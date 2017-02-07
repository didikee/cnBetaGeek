package com.didikee.cnbetareader.test;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by didik 
 * Created time 2017/2/7
 * Description: 
 */

public interface RxDouBanService {
    @GET("top250")
    Observable<DouBan> getDouBanTop250(@Query("start") int start, @Query("count") int count);
}
