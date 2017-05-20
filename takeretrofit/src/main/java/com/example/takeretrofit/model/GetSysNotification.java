package com.example.takeretrofit.model;

import android.util.Log;

import com.example.takeretrofit.api.AuthenticationApi;
import com.example.takeretrofit.api.SysNotificationApi;
import com.example.takeretrofit.bean.sysnotification.NotificationDatum;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResultSubscriber;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultMsgSubscriber;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultWithoutData;
import com.example.takeretrofit.utils.TransFormUtils;

import java.util.List;

/**
 * Created by price on 3/15/2017.
 */

public class GetSysNotification {
    //获取系统信息
    private String tag = "SysNotification_test";
    public void getSysNotification(String token,String page,String size){
        Log.e(tag,"is run???");
        ServiceFactory.getInstance().createService(SysNotificationApi.class)
                .getSysNotification(token,page,size)
                .compose(TransFormUtils.<HttpResult<List<NotificationDatum>>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<List<NotificationDatum>>() {
                    @Override
                    public void onSuccessWithData(List<NotificationDatum> notificationData) {
                        Log.e(tag,notificationData.get(0).getContent());
                    }

                    @Override
                    public void onSuccessWithMsg(HttpResult<List<NotificationDatum>> httpResult) {
                        Log.e(tag,httpResult.getMsg());
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(tag,throwable.getMessage());
                    }
                });
    }
}
