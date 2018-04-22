package com.price.take_new.service.modelService;

import com.price.take_new.service.api.OnPublishListener;

/**
 * Created by price on 2/21/2017.
 */

public interface IPublishExpressModel {
    void publish(String token, String company, String des, String address,
                 String place, int from_weixin, String nickname,
                 int weight_type, int at_school, String sms_content, boolean moneyType,
                 String reward, OnPublishListener onPublishListener);
}
