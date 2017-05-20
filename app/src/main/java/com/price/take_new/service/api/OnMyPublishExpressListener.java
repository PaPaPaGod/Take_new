package com.price.take_new.service.api;

import com.example.takeretrofit.bean.mydeliveryorder.MyDeliveryOrderDatum;

import java.util.List;

/**
 * Created by price on 2/23/2017.
 */

public interface OnMyPublishExpressListener extends OnMyExpressListener{
    void onMySuccess(List<MyDeliveryOrderDatum> mData, int code);
}
