package com.didikee.cnbetareader.network.services;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by didik 
 * Created time 2017/2/7
 * Description: 
 */

public interface Top10Service {
    @GET("ww")
    Observable<String> getTop10();
}
