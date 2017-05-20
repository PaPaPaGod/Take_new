package com.example.takeretrofit.api;

import com.example.takeretrofit.Config;
import com.example.takeretrofit.bean.delivery.DeliveryDatum;
import com.example.takeretrofit.bean.sysnotification.NotificationDatum;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultWithoutData;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by price on 3/15/2017.
 */

public interface SysNotificationApi {
    String BASE_URL = Config.SYSTEM_URL;
    @FormUrlEncoded
    @POST("getMessageList")
    Observable<HttpResult<List<NotificationDatum>>> getSysNotification(@Field(Config.KEY_TOKEN) String token,
                                              @Field(Config.KEY_PAGE) String page,
                                              @Field(Config.KEY_SIZE) String size);
}
