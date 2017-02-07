package com.didikee.cnbetareader.network;

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
    Observable<String> getNewsDetailByUrl(@Url String url);
}
