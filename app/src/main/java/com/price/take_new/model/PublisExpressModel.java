package com.price.take_new.model;

import android.util.Log;

import com.example.takeretrofit.api.CreateDevileryApi;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultMsgSubscriber;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultWithoutData;
import com.example.takeretrofit.utils.TransFormUtils;
import com.price.take_new.Constant;
import com.price.take_new.service.api.OnPublishListener;
import com.price.take_new.service.modelService.IPublishExpressModel;

/**
 * Created by price on 2/21/2017.
 */

public class PublisExpressModel implements IPublishExpressModel {

    //创建快递订单
    private String tag = "CreateDevilery_test";

    @Override
    public void publish(String token, String company, String des, String address, String place,
                        String price, String take_time, String sms_content,final OnPublishListener onPublishListener) {
        ServiceFactory.getInstance().createService(CreateDevileryApi.class)
                .createOrder(token,company,des,address,place,price,take_time,sms_content)
                .compose(TransFormUtils.<HttpResultWithoutData>defaultSchedulers())
                .subscribe(new HttpResultMsgSubscriber() {
                    @Override
                    public void onSuccessWithMsg(String result) {
                        Log.e(tag,result);
                        onPublishListener.onSuccess(result, Constant.SUCCESS_WITH_MSG);
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(tag,throwable.getMessage());
                        onPublishListener.onError(throwable,Constant.FAILED_WITH_EXCEPTION);
                    }
                });
    }
}
