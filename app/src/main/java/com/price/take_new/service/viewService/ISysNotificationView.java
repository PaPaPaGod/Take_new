package com.price.take_new.service.viewService;

import com.example.takeretrofit.bean.mydeliveryorder.MyDeliveryOrderDatum;
import com.example.takeretrofit.bean.myhelpdeliveryorder.MyHelpOrderDatum;
import com.example.takeretrofit.bean.sysnotification.NotificationDatum;

import java.util.List;

/**
 * Created by price on 3/15/2017.
 */

public interface ISysNotificationView {
    void showData(List<NotificationDatum> mData);
    void showToast(String msg, int code);
}
