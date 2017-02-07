package com.didikee.cnbetareader.test;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by didik 
 * Created time 2017/2/7
 * Description: 
 */

public interface DouBanService {
    @GET("top250")
    Call<DouBan> getDouBanTop250(@Query("start")int start,@Query("count")int count);
}
