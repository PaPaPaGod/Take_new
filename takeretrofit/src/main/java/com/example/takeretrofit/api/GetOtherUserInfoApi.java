package com.example.takeretrofit.api;


import com.example.takeretrofit.Config;
import com.example.takeretrofit.bean.otheruserinfo.OtherUserInfoData;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by price on 1/14/2017.
 */

public interface GetOtherUserInfoApi {
    String BASE_URL = Config.USER_URL;

    @FormUrlEncoded
    @POST("otherMsg")
    Observable<HttpResult<OtherUserInfoData>> getOtherUserInfo(@Field(Config.KEY_TOKEN) String token,
                                                               @Field(Config.KEY_USER_ID) String user_id);
}
