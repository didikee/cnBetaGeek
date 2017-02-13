package com.didikee.cnbetareader.network.services;

import com.didikee.cnbetareader.bean.NewsDetailBean;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by didik 
 * Created time 2017/2/7
 * Description: 
 */

public interface NewsDetailService {
    @GET
    Observable<NewsDetailBean> getNewsDetailByUrl(@Url String url);
}
