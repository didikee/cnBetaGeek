package com.didikee.cnbetareader.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.didikee.cnbetareader.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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
        String baseUrl = "https://api.douban.com/v2/movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DouBanService douBanService = retrofit.create(DouBanService.class);
        Call<DouBan> douBanCall = douBanService.getDouBanTop250(0, 10);
        douBanCall.enqueue(new Callback<DouBan>() {
            @Override
            public void onResponse(Call<DouBan> call, Response<DouBan> response) {
                if (response != null) {
                    tvShow.setText(response.body().getTitle());
                }

            }

            @Override
            public void onFailure(Call<DouBan> call, Throwable t) {

            }
        });

    }
}
