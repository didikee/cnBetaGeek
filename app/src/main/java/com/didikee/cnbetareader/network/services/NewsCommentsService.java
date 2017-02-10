package com.didikee.cnbetareader.network.services;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by didik 
 * Created time 2017/2/10
 * Description: 
 */

public interface NewsCommentsService {
    @GET
    Observable<String> getNewsComments(@Url String commentsUrl);
}
