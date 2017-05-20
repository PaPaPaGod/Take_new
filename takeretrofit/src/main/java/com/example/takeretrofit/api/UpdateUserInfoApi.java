package com.example.takeretrofit.api;


import com.example.takeretrofit.Config;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by price on 1/14/2017.
 */

public interface UpdateUserInfoApi {
    String BASE_URL = Config.USER_URL;
    @FormUrlEncoded
    @POST("updateUserMsg")
    Observable<HttpResult<String>> updateUserInfo(@Field(Config.KEY_TOKEN) String token,
                                                  @Field(Config.KEY_NAME) String name,
                                                  @Field(Config.KEY_SEX) String sex,
                                                  @Field(Config.KEY_SCHOOL_ID) String school_id,
                                                  @Field(Config.KEY_MAJOR) String major);
}
