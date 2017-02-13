package com.didikee.cnbetareader.network;

import android.util.Log;

import com.didikee.cnbetareader.bean.ArticleListBean;
import com.didikee.cnbetareader.bean.CommentBeanList;
import com.didikee.cnbetareader.bean.NewsDetailBean;
import com.didikee.cnbetareader.network.services.DefaultArticleListService;
import com.didikee.cnbetareader.network.services.NewsCommentsService;
import com.didikee.cnbetareader.network.services.NewsDetailService;
import com.didikee.cnbetareader.test.DouBan;
import com.didikee.cnbetareader.test.RxDouBanService;

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

    public void getNewsDetailByNewsId(Subscriber<NewsDetailBean> subscriber, String newsId) {
        String url = RequestUrl.getNewsDetailUrl(newsId);
        Log.e("test","url: "+url);
        HttpService.getInstance().create(NewsDetailService
                .class)
                .getNewsDetailByUrl(url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取默认的文章列表
     * @param subscriber
     * @param lastSid 最后一个文章的sid,没有时填写 Integer.MAX_VALUE
     */
    public void getDefaultArticleList(Subscriber<ArticleListBean> subscriber, String lastSid){
        String url = RequestUrl.getArticleListUrl(lastSid);
        Log.e("test","url: "+url);
        HttpService.getInstance()
                .create(DefaultArticleListService.class)
                .getDefaultArticleList(url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getNewsComments(Subscriber<CommentBeanList> subscriber, String sid, int page){
        String url = RequestUrl.getCommentsUrl(sid,page);
        Log.e("test","url: "+url);
        HttpService.getInstance()
                .create(NewsCommentsService.class)
                .getNewsComments(url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
