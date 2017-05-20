package com.price.take_new.service.modelService;

import com.price.take_new.service.api.OnPublishListener;

/**
 * Created by price on 2/21/2017.
 */

public interface IPublishExpressModel {
    void publish(String token, String company, String des, String address,
                 String place, String price, String take_time, String sms_content,OnPublishListener onPublishListener);
}
