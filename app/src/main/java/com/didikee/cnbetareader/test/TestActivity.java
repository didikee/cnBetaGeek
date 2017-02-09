package com.didikee.cnbetareader.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.didikee.cnbetareader.R;
import com.didikee.cnbetareader.bean.ArticleListBean;
import com.didikee.cnbetareader.network.HttpMethods;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;


public class TestActivity extends AppCompatActivity {

    @BindView(R.id.tv_show)
    TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);


        getDouBanMovie();
    }

    private void getDouBanMovie() {
//        String baseUrl = "https://api.douban.com/v2/movie/";
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        RxDouBanService douBanService = retrofit.create(RxDouBanService.class);
//        douBanService.getDouBanTop250(0, 10)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<DouBan>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(DouBan douBan) {
//
//                    }
//                });
//        douBanCall.enqueue(new Callback<DouBan>() {
//            @Override
//            public void onResponse(Call<DouBan> call, Response<DouBan> response) {
//                if (response != null) {
//                    tvShow.setText(response.body().getTitle());
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<DouBan> call, Throwable t) {
//
//            }
//        });

//        HttpMethods.getInstance().getTopMovie(new Subscriber<DouBan>() {
//            @Override
//            public void onCompleted() {
//                Toast.makeText(TestActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(DouBan douBan) {
//                if (douBan!=null){
//                    tvShow.setText(douBan.getTitle());
//                }
//            }
//        },0,10);

//        HttpMethods.getInstance().getNewsDetailByNewsId(new Subscriber<NewsDetail>() {
//            @Override
//            public void onCompleted() {
//                Toast.makeText(TestActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(NewsDetail newsDetail) {
//                tvShow.setText(newsDetail.getResult().getTitle());
//            }
//        },581785);

        String sid= Integer.MAX_VALUE +"";
        HttpMethods.getInstance().getDefaultArticleList(new Subscriber<ArticleListBean>() {
            @Override
            public void onCompleted() {
                Toast.makeText(TestActivity.this, "哈哈", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ArticleListBean s) {

            }
        },sid);
    }
}
