package com.didikee.cnbetareader.network;

import android.util.Log;

import com.didikee.cnbetareader.bean.NewsDetail;
import com.didikee.cnbetareader.network.services.DefaultArticleList;
import com.didikee.cnbetareader.network.services.NewsDetailService;
import com.didikee.cnbetareader.test.DouBan;
import com.didikee.cnbetareader.test.RxDouBanService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by didik 
 * Created time 2017/2/7
 * Description: 
 */

public class HttpMethods {
    private static class Singleton {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    public static HttpMethods getInstance() {
        return Singleton.INSTANCE;
    }

    public void getTopMovie(Subscriber<DouBan> subscriber, int start, int count) {
        HttpService.getInstance().create(RxDouBanService.class)
                .getDouBanTop250(start, count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getNewsDetailByNewsId(Subscriber<NewsDetail> subscriber, long newsId) {
        String url = RequestUrl.getNewsDetailUrl(newsId+"");
        Log.e("test","url: "+url);
        HttpService.getInstance().create(NewsDetailService
                .class)
                .getNewsDetailByUrl(url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getDefaultArticleList(Subscriber<String> subscriber, String lastSid){
        String url = RequestUrl.getArticleListUrl(lastSid);
        Log.e("test","url: "+url);
        handleAll(subscriber,HttpService.getInstance().create(DefaultArticleList.class).getDefaultArticleList(url));
    }

    private void handleAll(Subscriber subscriber, Observable observable) {
        if (subscriber == null || observable == null) return;

        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
