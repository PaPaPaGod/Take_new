package com.price.take_new.service.api;

import com.example.takeretrofit.bean.delivery.DeliveryDatum;
import com.example.takeretrofit.bean.userinfo.UserInfoData;
import com.price.take_new.utils.item.AllExpressOrderItem;

import java.util.List;

/**
 * 获取快递页列表
 * Created by price on 2/20/2017.
 */

public interface OnExpressListener {
    void onSuccess(List<DeliveryDatum> deliveryDatum, int success);
    void onMsg(String msg,int hint);
    void onError(Throwable throwable,int error);
}
