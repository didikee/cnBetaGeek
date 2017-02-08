package com.didikee.cnbetareader.network.services;

import com.didikee.cnbetareader.bean.NewsDetail;

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
    Observable<NewsDetail> getNewsDetailByUrl(@Url String url);
}
