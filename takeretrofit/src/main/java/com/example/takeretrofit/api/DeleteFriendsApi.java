package com.example.takeretrofit.api;

import com.example.takeretrofit.Config;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultWithoutData;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by intel on 3/29/2018.
 */

public interface DeleteFriendsApi {
    String BASE_URL = Config.USER_URL;
    @FormUrlEncoded
    @POST("deleteFriend")
    Observable<HttpResultWithoutData> deleteFriend(@Field(Config.KEY_TOKEN) String token,
                                                   @Field(Config.KEY_USER_ID) String user_id);
}
