package com.didikee.cnbetareader.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.didikee.cnbetareader.R;
import com.didikee.cnbetareader.bean.Keys;
import com.didikee.cnbetareader.bean.NewsDetail;
import com.didikee.cnbetareader.network.HttpMethods;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

public class NewsDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.tv_shortDesc)
    TextView tvShortDesc;
    @BindView(R.id.tv_content)
    TextView tvContent;

    private String curSid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        getCurSid(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getCurSid(intent);
    }

    private void getCurSid(Intent intent){
        if (intent != null ){
            String sid = intent.getStringExtra(Keys.SID);
            if (!TextUtils.isEmpty(sid)){
                curSid = sid;
                requestNewsDetail();
                return;
            }
        }
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void requestNewsDetail(){
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
        if (newsDetail == null ){
            finish();
            return;
        }
        String status = newsDetail.getStatus();
        if (!Keys.RESULT_OK.equalsIgnoreCase(status)){
            finish();
            return;
        }
        NewsDetail.ResultBean bean = newsDetail.getResult();
        tvTitle.setText(bean.getTitle());
//        contentDesc.setText(getString(R.string.content_desc,
//                ContentUtil.getPrettyTime(ContentActivity.this, article.getTime()),
//                Html.fromHtml(article.getSource()), article.getCounter(),
//                article.getGood(), article.getComments()));
        tvInfo.setText(
                getString(R.string.news_info, bean.getTime(),
                Html.fromHtml(bean.getSource()),
                bean.getCounter(),
                bean.getGood(),
                bean.getComments()));

        tvShortDesc.setText(Html.fromHtml(bean.getHometext()));
        tvContent.setText(Html.fromHtml(bean.getBodytext()));
    }
}
