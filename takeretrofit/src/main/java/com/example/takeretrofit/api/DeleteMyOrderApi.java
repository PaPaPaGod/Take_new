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

public interface DeleteMyOrderApi {
        String BASE_URL = Config.EXPRESS_URL;
        @FormUrlEncoded
        @POST("deleteOrder")
        Observable<HttpResultWithoutData> delete(@Field(Config.KEY_TOKEN) String token,
                                                 @Field(Config.KEY_ORDER_ID) String order_id);
}
