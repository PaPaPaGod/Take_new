package com.price.take_new.service.api;

import com.example.takeretrofit.bean.mydeliveryorder.MyDeliveryOrderDatum;
import com.example.takeretrofit.bean.myhelpdeliveryorder.MyHelpOrderDatum;

import java.util.List;

/**
 * 代拿快递与我的快递
 * Created by price on 2/23/2017.
 */

public interface OnMyExpressListener {
//    void onMySuccess(List<MyDeliveryOrderDatum> mData, int code);
//    void onHelpSuccess(List<MyHelpOrderDatum> mData, int code);
    void onMsg(String msg,int code);
    void onError(String msg,int code);
}
