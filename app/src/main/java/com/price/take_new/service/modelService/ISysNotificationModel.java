package com.price.take_new.service.modelService;

import com.price.take_new.service.api.OnSysNotificationListener;

/**
 * Created by price on 3/15/2017.
 */

public interface ISysNotificationModel {
    void getExpress(String token, String page, String size, OnSysNotificationListener listener);
}
