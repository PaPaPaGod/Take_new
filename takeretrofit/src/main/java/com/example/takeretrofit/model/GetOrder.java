package com.example.takeretrofit.model;

import android.util.Log;

import com.example.takeretrofit.api.GetOrderApi;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultMsgSubscriber;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultWithoutData;
import com.example.takeretrofit.utils.TransFormUtils;


/**
 * Created by price on 1/15/2017.
 */

public class GetOrder {
    //接受订单

    private String tag = "getOrder_test";

    public void getOrder(String token,String poster_id,String express_id){
        ServiceFactory.getInstance().createService(GetOrderApi.class)
                .getOrder(token,poster_id,express_id)
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
