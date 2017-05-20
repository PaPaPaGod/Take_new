package com.example.takeretrofit.api;


import com.example.takeretrofit.Config;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultWithoutData;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by price on 1/15/2017.
 */

public interface GetOrderApi {
    //接受订单
    String BASE_URL = Config.EXPRESS_URL;

    @FormUrlEncoded
    @POST("getOrder")
    Observable<HttpResultWithoutData> getOrder(@Field(Config.KEY_TOKEN) String token,
                                               @Field(Config.KEY_POSTER_ID) String poster_id,
                                               @Field(Config.KEY_EXPRESS_ID) String express_id);
}
