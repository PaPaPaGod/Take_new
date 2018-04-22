package com.price.take_new.service.modelService;

import com.price.take_new.service.api.OnManagerExpressListener;

/**
 * Created by price on 2/24/2017.
 */

public interface IManagerExpressModel {
    void update(String token, String order_id, String company, String address,
                String place, String nickname,
                int weight_type, String des, int at_school, String sms_content,
                String money, String small_reward,
                OnManagerExpressListener onManagerExpressListener);
    void delete(String token, String order_id, OnManagerExpressListener onManagerExpressListener);
}
