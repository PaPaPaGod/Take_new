package com.price.take_new.service.api;

import com.example.takeretrofit.bean.myhelpdeliveryorder.MyHelpOrderDatum;

import java.util.List;

/**
 * Created by price on 2/23/2017.
 */

public interface OnMyHelpExpressListener extends OnMyExpressListener {
    void onHelpSuccess(List<MyHelpOrderDatum> mData, int code);
}
