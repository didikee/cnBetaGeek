package com.didikee.cnbetareader.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.didikee.cnbetareader.R;
import com.didikee.cnbetareader.bean.Keys;
import com.didikee.cnbetareader.bean.NewsDetail;
import com.didikee.cnbetareader.network.HttpMethods;
import com.didikee.cnbetareader.ui.views.htmlTextView.PicassoImageGetter;
import com.didikee.cnbetareader.utils.HtmlUtil;
import com.didikee.uilibs.utils.DisplayUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

public class NewsDetailActivity extends BaseCnBetaActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.tv_shortDesc)
    TextView tvShortDesc;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    private String curSid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
//        setToolBar(new Title("详情",true));
        setToolBar();
        getCurSid(getIntent());
    }

    private void setToolBar() {

//        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("详情");
//        toolbar.setSubtitle("次级标题");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getCurSid(intent);
    }

    private void getCurSid(Intent intent) {
        if (intent != null) {
            String sid = intent.getStringExtra(Keys.SID);
            if (!TextUtils.isEmpty(sid)) {
                curSid = sid;
                requestNewsDetail();
                return;
            }
        }
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void requestNewsDetail() {
        HttpMethods.getInstance().getNewsDetailByNewsId(new Subscriber<NewsDetail>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewsDetail newsDetail) {
                bindData(newsDetail);
            }
        }, curSid);
    }

    private void bindData(NewsDetail newsDetail) {
        if (newsDetail == null) {
            finish();
            return;
        }
        String status = newsDetail.getStatus();
        if (!Keys.RESULT_OK.equalsIgnoreCase(status)) {
            finish();
            return;
        }
        NewsDetail.ResultBean bean = newsDetail.getResult();
        tvTitle.setText(bean.getTitle());
        tvInfo.setText(
                getString(R.string.news_info, bean.getTime(),
                        Html.fromHtml(bean.getSource()),
                        bean.getCounter(),
                        bean.getGood(),
                        bean.getComments()));
        tvShortDesc.setText(Html.fromHtml(HtmlUtil.htmlFilter(bean.getHometext())));
        boolean isSaveNetWork = true;
        if (isSaveNetWork) {
            int imageWidth = this.getResources().getDisplayMetrics().widthPixels - DisplayUtil.dp2px(this,
                    10);
//            URLImageParser p = new URLImageParser(this,imageWidth);
//            Spanned htmlSpan = Html.fromHtml(bean.getBodytext(), p, null);
//            Spanned htmlSpan = Html.fromHtml(bean.getBodytext(), new CnBetaImageGetter(this,imageWidth), null);
//            tvContent.setText(htmlSpan);

//            Spanned spanned = Html.fromHtml(bean.getBodytext(), new MyImageGetter(this, tvContent), new MyTagHandler(this));
//            tvContent.setText(spanned);
//            tvContent.setMovementMethod(LinkMovementMethod.getInstance());
            tvContent.setMovementMethod(new LinkMovementMethod());
            tvContent.setText(Html.fromHtml(bean.getBodytext(),
                    new PicassoImageGetter(tvContent), null));
        } else {
            // 省流量模式
            tvContent.setText(Html.fromHtml(bean.getBodytext()));
        }

    }
}
