package com.example.takeretrofit.api;


import com.example.takeretrofit.Config;
import com.example.takeretrofit.bean.appliedlist.AppliedListData;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultWithoutData;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by price on 1/14/2017.
 */

public interface AppliedFriendListApi {
    String BASE_URL = Config.USER_URL;
    @FormUrlEncoded
    @POST("applyList")
    Observable<HttpResult<List<AppliedListData>>> getAppliedList(@Field("token") String token,
                                                               @Field("page") String page,
                                                               @Field("size") String size);
}
