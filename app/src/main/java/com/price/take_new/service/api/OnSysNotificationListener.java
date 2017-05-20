package com.price.take_new.service.api;

import com.example.takeretrofit.bean.sysnotification.NotificationDatum;

import java.util.List;

/**
 * Created by price on 3/15/2017.
 */

public interface OnSysNotificationListener {
    void onSuccess(List<NotificationDatum> mData);
    void onMsg(String msg);
    void onError(String msg);
}
