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

public interface CreateDevileryApi {
    String BASE_URL = Config.EXPRESS_URL;
    @FormUrlEncoded
    @POST("createOrder")
    Observable<HttpResultWithoutData> createOrder(@Field(Config.KEY_TOKEN) String toke,
                                                  @Field(Config.KEY_COMPANY) String company,
                                                  @Field(Config.KEY_DES) String des,
                                                  @Field(Config.KEY_ADDRESS) String address,
                                                  @Field(Config.KEY_PLACE) String place,
                                                  @Field(Config.KEY_PRICE) String price,
                                                  @Field(Config.KEY_TAKE_TIME) String take_time,
                                                  @Field(Config.KEY_SMS_CONTENT) String sms_content);
}
