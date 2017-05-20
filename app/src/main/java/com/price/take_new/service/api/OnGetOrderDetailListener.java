package com.price.take_new.service.api;

import com.example.takeretrofit.bean.orderdetail.OrderDetailData;

/**
 * 获取快递详情
 * Created by price on 3/16/2017.
 */

public interface OnGetOrderDetailListener {
    void onSuccess(OrderDetailData data);
    void onMsg(String msg);
    void onError(String msg);
}
