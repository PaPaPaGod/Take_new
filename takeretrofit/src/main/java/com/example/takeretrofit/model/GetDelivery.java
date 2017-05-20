package com.example.takeretrofit.model;

import android.util.Log;


import com.example.takeretrofit.api.DeliveryApi;
import com.example.takeretrofit.bean.delivery.DeliveryDatum;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResultSubscriber;
import com.example.takeretrofit.utils.TransFormUtils;

import java.util.List;

/**
 * Created by price on 1/13/2017.
 */

public class GetDelivery {
    //获取快递列表
    private static final String TAG = "delivery_test";
    public void getDelivery(String token,String page){
        ServiceFactory.getInstance().createService(DeliveryApi.class)
                .getDelivery(token, page)
                .compose(TransFormUtils.<HttpResult<List<DeliveryDatum>>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<List<DeliveryDatum>>() {
                    @Override
                    public void onSuccessWithData(List<DeliveryDatum> deliveryData) {
                        Log.e(TAG,deliveryData.get(0).getName());
                    }

                    @Override
                    public void onSuccessWithMsg(HttpResult<List<DeliveryDatum>> httpResult) {
                        Log.e(TAG,httpResult.getMsg());
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(TAG,throwable.getMessage());
                    }
                });
    }
}
