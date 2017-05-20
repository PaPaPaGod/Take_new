package com.price.take_new.model;

import android.util.Log;

import com.example.takeretrofit.api.SysNotificationApi;
import com.example.takeretrofit.bean.sysnotification.NotificationDatum;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResultSubscriber;
import com.example.takeretrofit.utils.TransFormUtils;
import com.price.take_new.service.api.OnSysNotificationListener;
import com.price.take_new.service.modelService.ISysNotificationModel;

import java.util.List;

/**
 * Created by price on 3/15/2017.
 */

public class SysNotificationModel implements ISysNotificationModel{

    private String tag = "SysNotification_test";

    @Override
    public void getExpress(String token, String page, String size, final OnSysNotificationListener listener) {
        ServiceFactory.getInstance().createService(SysNotificationApi.class)
                .getSysNotification(token,page,size)
                .compose(TransFormUtils.<HttpResult<List<NotificationDatum>>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<List<NotificationDatum>>() {
                    @Override
                    public void onSuccessWithData(List<NotificationDatum> notificationData) {
                        Log.e(tag,notificationData.toString());
                        listener.onSuccess(notificationData);
                    }

                    @Override
                    public void onSuccessWithMsg(HttpResult<List<NotificationDatum>> httpResult) {
                        Log.e(tag,httpResult.getMsg());
                        listener.onMsg(httpResult.getMsg());
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(tag,throwable.getMessage());
                        listener.onError(throwable.getMessage());
                    }
                });
    }
}
