package com.example.takeretrofit.model;

import android.util.Log;


import com.example.takeretrofit.api.GetMyDeliveryOrderApi;
import com.example.takeretrofit.bean.mydeliveryorder.MyDeliveryOrderDatum;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResultSubscriber;
import com.example.takeretrofit.utils.TransFormUtils;

import java.util.List;

/**
 * Created by price on 1/15/2017.
 */

public class GetMyDelivery {
    //我发布的快递
    private String tag = "getMyDelivery_test";
    public void getMyDelivery(String token,String page){
        ServiceFactory.getInstance().createService(GetMyDeliveryOrderApi.class)
                .getMyPostOrder(token,page)
                .compose(TransFormUtils.<HttpResult<List<MyDeliveryOrderDatum>>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<List<MyDeliveryOrderDatum>>() {
                    @Override
                    public void onSuccessWithData(List<MyDeliveryOrderDatum> myDeliveryOrderData) {
                        Log.e(tag,myDeliveryOrderData.get(0).getId());
                    }

                    @Override
                    public void onSuccessWithMsg(HttpResult<List<MyDeliveryOrderDatum>> httpResult) {
                        Log.e(tag,httpResult.getMsg());
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(tag,throwable.getMessage());
                    }
                });
    }
}
