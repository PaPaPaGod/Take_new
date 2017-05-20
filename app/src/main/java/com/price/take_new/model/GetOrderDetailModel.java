package com.price.take_new.model;

import android.util.Log;

import com.example.takeretrofit.api.GetOrderDetailApi;
import com.example.takeretrofit.bean.orderdetail.OrderDetailData;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResultSubscriber;
import com.example.takeretrofit.utils.TransFormUtils;
import com.price.take_new.service.api.OnGetOrderDetailListener;
import com.price.take_new.service.modelService.IGetOrderDetailModel;

/**
 * Created by price on 3/16/2017.
 */

public class GetOrderDetailModel implements IGetOrderDetailModel {

    //获取订单详情
    private String tag = "getOrderDetail_test";

    @Override
    public void getOrderDetail(String token, String order_id, final OnGetOrderDetailListener onGetOrderDetailListener) {
        ServiceFactory.getInstance().createService(GetOrderDetailApi.class)
                .getOrderDetail(token, order_id)
                .compose(TransFormUtils.<HttpResult<OrderDetailData>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<OrderDetailData>() {
                    @Override
                    public void onSuccessWithData(OrderDetailData orderDetailData) {
                        Log.e(tag,orderDetailData.getAddress());
                        onGetOrderDetailListener.onSuccess(orderDetailData);
                    }

                    @Override
                    public void onSuccessWithMsg(HttpResult<OrderDetailData> httpResult) {
                        Log.e(tag,httpResult.getMsg());
                        onGetOrderDetailListener.onMsg(httpResult.getMsg());
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(tag,throwable.getMessage());
                        onGetOrderDetailListener.onError(throwable.getMessage());
                    }
                });
    }
}
