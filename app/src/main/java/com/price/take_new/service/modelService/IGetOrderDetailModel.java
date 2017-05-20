package com.price.take_new.service.modelService;

import com.price.take_new.service.api.OnGetOrderDetailListener;

/**
 * Created by price on 3/16/2017.
 */

public interface IGetOrderDetailModel {
    void getOrderDetail(String token, String order_id, OnGetOrderDetailListener onGetOrderDetailListener);
}
