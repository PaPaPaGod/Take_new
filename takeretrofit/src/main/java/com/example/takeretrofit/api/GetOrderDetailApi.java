package com.example.takeretrofit.api;


import com.example.takeretrofit.Config;
import com.example.takeretrofit.bean.orderdetail.OrderDetailData;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by price on 1/15/2017.
 */

public interface GetOrderDetailApi {
    String BASE_URL = Config.EXPRESS_URL;
    @FormUrlEncoded
    @POST("orderDetail")
    Observable<HttpResult<OrderDetailData>> getOrderDetail(@Field(Config.KEY_TOKEN) String token,
                                                           @Field(Config.KEY_ORDER_ID) String order_id);
}
