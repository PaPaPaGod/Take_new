package com.example.takeretrofit.api;


import com.example.takeretrofit.Config;
import com.example.takeretrofit.bean.mydeliveryorder.MyDeliveryOrderDatum;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by price on 1/15/2017.
 */

public interface GetMyDeliveryOrderApi {
    String BASE_URL = Config.EXPRESS_URL;
    @FormUrlEncoded
    @POST("myPostOrder")
    Observable<HttpResult<List<MyDeliveryOrderDatum>>> getMyPostOrder(@Field(Config.KEY_TOKEN) String token,
                                                                      @Field(Config.KEY_PAGE) String page);
}
