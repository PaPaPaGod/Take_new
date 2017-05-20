package com.price.take_new.service.modelService;

import com.price.take_new.service.api.OnManagerExpressListener;

/**
 * Created by price on 2/24/2017.
 */

public interface IManagerExpressModel {
    void update(String token, String order_id,String company, String des, String address,
                String place, String price, String take_time,
                OnManagerExpressListener onManagerExpressListener);
    void delete(String token,String order_id,OnManagerExpressListener onManagerExpressListener);
}
