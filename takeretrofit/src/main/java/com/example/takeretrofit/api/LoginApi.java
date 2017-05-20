package com.example.takeretrofit.api;


import com.example.takeretrofit.Config;
import com.example.takeretrofit.bean.login.LoginData;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by price on 1/12/2017.
 */

public interface LoginApi {

    String BASE_URL = Config.REGISTER_OR_LOGIN_URL;

    @FormUrlEncoded
    @POST("login")
    Observable<HttpResult<LoginData>> login(@Field("phone_num") String num,
                                            @Field("password") String password);
}
