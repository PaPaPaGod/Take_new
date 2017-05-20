package com.example.takeretrofit.model;

import android.util.Log;

import com.example.takeretrofit.api.UpdateMyOrderApi;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultMsgSubscriber;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultWithoutData;
import com.example.takeretrofit.utils.TransFormUtils;


/**
 * Created by price on 1/15/2017.
 */

public class UpdateOrder {
    //更新已发布的订单信息
    private String tag = "deleteorder_test";
    public void updateOrder(String token,String order_id,String company,String des,String address,
                            String take_time,String place,String price){
        ServiceFactory.getInstance().createService(UpdateMyOrderApi.class)
                .update(token,order_id,company,des,address,take_time,place,price)
                .compose(TransFormUtils.<HttpResultWithoutData>defaultSchedulers())
                .subscribe(new HttpResultMsgSubscriber() {
                    @Override
                    public void onSuccessWithMsg(String result) {
                        Log.e(tag,result);
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(tag,throwable.getMessage());
                    }
                });
    }
}

