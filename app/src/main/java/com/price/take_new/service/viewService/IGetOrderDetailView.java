package com.price.take_new.service.viewService;

import com.example.takeretrofit.bean.orderdetail.OrderDetailData;

/**
 * Created by price on 3/16/2017.
 */

public interface IGetOrderDetailView {
    void onBindOrderDetail(OrderDetailData data);
    void showToast(String msg);
}
