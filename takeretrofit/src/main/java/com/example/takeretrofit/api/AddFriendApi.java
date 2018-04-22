package com.example.takeretrofit.api;


import com.example.takeretrofit.Config;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultWithoutData;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by price on 1/14/2017.
 */

public interface AddFriendApi {
    String BASE_URL = Config.USER_URL;
    @FormUrlEncoded
    @POST("applyFriend")
    Observable<HttpResultWithoutData> addFriendApi(@Field("token") String token,
                                                   @Field("user_id") String user_id);
}
