package com.example.takeretrofit.api;

import com.example.takeretrofit.Config;
import com.example.takeretrofit.bean.delivery.DeliveryDatum;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by price on 1/13/2017.
 */

public interface DeliveryApi {
    String BASE_URL = Config.EXPRESS_URL;
    @FormUrlEncoded
    @POST("orderList")
    Observable<HttpResult<List<DeliveryDatum>>> getDelivery(@Field("token") String token,
                                                            @Field("page") int page,
                                                            @Field("filter") String filter);
}
