package com.price.take_new.service.viewService;

import com.example.takeretrofit.bean.mydeliveryorder.MyDeliveryOrderDatum;
import com.example.takeretrofit.bean.myhelpdeliveryorder.MyHelpOrderDatum;
import com.price.take_new.utils.item.PersonalExpressItem;

import java.util.List;

/**
 * Created by price on 2/23/2017.
 */

public interface IMyExpressView {
    void showHelpData(List<MyHelpOrderDatum> mData);
    void showMyData(List<MyDeliveryOrderDatum> mData);
    void showToast(String msg, int code);
    void showLoading(int index);
    void hideLoading(int index);
}
