package com.example.takeretrofit.api;


import com.example.takeretrofit.Config;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultWithoutData;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by price on 1/14/2017.
 */

public interface RegisterApi {
    String BASE_URL = Config.REGISTER_OR_LOGIN_URL;

    @FormUrlEncoded
    @POST("register")
    Observable<HttpResultWithoutData> register(@Field(Config.KEY_PHONE_NUM) String phone_num,
                                               @Field(Config.KEY_PASSWORD) String password);
}
