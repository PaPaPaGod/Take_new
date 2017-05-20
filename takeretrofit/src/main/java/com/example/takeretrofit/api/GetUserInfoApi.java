package com.example.takeretrofit.api;


import com.example.takeretrofit.Config;
import com.example.takeretrofit.bean.userinfo.UserInfoData;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by price on 1/14/2017.
 */

public interface GetUserInfoApi {
    String BASE_URL = Config.USER_URL;

    @FormUrlEncoded
    @POST("userDetail")
    Observable<HttpResult<UserInfoData>> getUserInfo(@Field(Config.KEY_TOKEN) String token);
}
