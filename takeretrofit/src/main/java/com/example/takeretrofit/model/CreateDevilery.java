package com.example.takeretrofit.model;

import android.util.Log;

import com.example.takeretrofit.api.CreateDevileryApi;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultMsgSubscriber;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultWithoutData;
import com.example.takeretrofit.utils.TransFormUtils;


/**
 * Created by price on 1/15/2017.
 */

public class CreateDevilery {
    //创建快递订单
    private String tag = "CreateDevilery_test";
    public void createDevilery(String token,String company,String des,String address,String place
                                       ,String price ,String take_time,String sms_content){
        ServiceFactory.getInstance().createService(CreateDevileryApi.class)
                .createOrder(token,company,des,address,place,price,take_time,sms_content)
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
