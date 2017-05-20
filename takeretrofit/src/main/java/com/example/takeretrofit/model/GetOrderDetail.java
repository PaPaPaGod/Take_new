package com.example.takeretrofit.model;

import android.util.Log;

import com.example.takeretrofit.api.GetOrderDetailApi;
import com.example.takeretrofit.bean.orderdetail.OrderDetailData;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResultSubscriber;
import com.example.takeretrofit.utils.TransFormUtils;


/**
 * Created by price on 1/15/2017.
 */

public class GetOrderDetail {
    //获取订单详情
    private String tag = "getOrderDetail_test";
    public void getOrderDetail(String token,String order_id){
        ServiceFactory.getInstance().createService(GetOrderDetailApi.class)
                .getOrderDetail(token, order_id)
                .compose(TransFormUtils.<HttpResult<OrderDetailData>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<OrderDetailData>() {
                    @Override
                    public void onSuccessWithData(OrderDetailData orderDetailData) {
                        Log.e(tag,orderDetailData.getAddress());
                    }

                    @Override
                    public void onSuccessWithMsg(HttpResult<OrderDetailData> httpResult) {
                        Log.e(tag,httpResult.getMsg());
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(tag,throwable.getMessage());
                    }
                });
    }
}
