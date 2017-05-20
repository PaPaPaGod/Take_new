package com.example.takeretrofit.model;

import android.util.Log;

import com.example.takeretrofit.api.DeleteMyOrderApi;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultMsgSubscriber;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultWithoutData;
import com.example.takeretrofit.utils.TransFormUtils;


/**
 * Created by price on 1/15/2017.
 */

public class DeleteOrder {
    private String tag = "deleteorder_test";
    //删除已发布的订单
    public void deleteOrder(String token,String order_id){
        ServiceFactory.getInstance().createService(DeleteMyOrderApi.class)
                .delete(token,order_id)
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
