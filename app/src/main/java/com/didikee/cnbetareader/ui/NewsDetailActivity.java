package com.didikee.cnbetareader.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.didikee.cnbetareader.R;
import com.didikee.cnbetareader.app.BaseCnBetaActivity;
import com.didikee.cnbetareader.bean.Keys;
import com.didikee.cnbetareader.bean.NewsDetailBean;
import com.didikee.cnbetareader.network.HttpMethods;
import com.didikee.cnbetareader.ui.views.htmlTextView.PicassoImageGetter;
import com.didikee.cnbetareader.utils.HtmlUtil;
import com.didikee.uilibs.utils.DisplayUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
//    @BindView(R.id.fab)
//    FloatingActionButton fab;
    @BindView(R.id.iv_btn)
    ImageView ivBtn;



    private String curSid = "";
    private int comments = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        setToolBar();
        getCurSid(getIntent());
    }

    private void setToolBar() {
        toolbar.setTitle("详情");
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
        HttpMethods.getInstance().getNewsDetailByNewsId(new Subscriber<NewsDetailBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewsDetailBean newsDetailBean) {
                bindData(newsDetailBean);
            }
        }, curSid);
    }

    private void bindData(NewsDetailBean newsDetailBean) {
        if (newsDetailBean == null) {
            finish();
            return;
        }
        String status = newsDetailBean.getStatus();
        if (!Keys.RESULT_OK.equalsIgnoreCase(status)) {
            finish();
            return;
        }
        NewsDetailBean.ResultBean bean = newsDetailBean.getResult();
        try {
            comments = Integer.valueOf(bean.getComments());
        } catch (NumberFormatException e) {
            // empty
        }
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
            tvContent.setMovementMethod(new LinkMovementMethod());
            tvContent.setText(Html.fromHtml(bean.getBodytext(),
                    new PicassoImageGetter(tvContent), null));
        } else {
            // 省流量模式
            tvContent.setText(Html.fromHtml(bean.getBodytext()));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_detail_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
//            case R.id.comment:
//                startCommentActivity();
//                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @OnClick(R.id.iv_btn)
    public void onClick(){
        startCommentActivity();
    }

    private void startCommentActivity(){
        if (TextUtils.isEmpty(curSid) || comments <=0){
            Toast.makeText(this, "暂时还没有评论~", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(NewsDetailActivity.this,CommentsActivity.class);
        intent.putExtra(Keys.SID,curSid);
        startActivity(intent);
    }
}
