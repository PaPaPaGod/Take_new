package com.price.take_new.model;

import android.util.Log;

import com.example.takeretrofit.api.CreateDevileryApi;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResultSubscriber;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultMsgSubscriber;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultWithoutData;
import com.example.takeretrofit.utils.TransFormUtils;
import com.price.take_new.Constant;
import com.price.take_new.service.api.OnPublishListener;
import com.price.take_new.service.modelService.IPublishExpressModel;

import java.util.List;

/**
 * Created by price on 2/21/2017.
 */

public class PublisExpressModel implements IPublishExpressModel {

    //创建快递订单
    private String tag = "CreateDevilery_test";

    @Override
    public void publish(String token, String company, String des, String address, String place,
                        int from_weixin, String nickname, int weight_type, int at_school,
                        String sms_content, boolean moneyType, String reward,
                        final OnPublishListener onPublishListener) {
        Log.e(tag,"publish");
        if(moneyType){
            Log.e(tag,"publish:"+moneyType);
            ServiceFactory.getInstance().createService(CreateDevileryApi.class)
                    .createOrder(token,company,address,place,nickname,weight_type,des,
                            at_school,sms_content,reward,null,null,from_weixin)
                    .compose(TransFormUtils.<HttpResultWithoutData>defaultSchedulers())
                    .subscribe(new HttpResultMsgSubscriber() {
                        @Override
                        public void onSuccessWithMsg(String result) {
                            Log.e(tag,result);
                            onPublishListener.onSuccess(result,Constant.SUCCESS_WITH_MSG);
                        }

                        @Override
                        public void _onError(Throwable throwable) {
                            Log.e(tag,throwable.getMessage());
                            onPublishListener.onError(throwable,Constant.FAILED_WITH_EXCEPTION);
                        }
                    });
        }else {
            Log.e(tag,"publish:"+moneyType);
            ServiceFactory.getInstance().createService(CreateDevileryApi.class)
                    .createOrder(token,company,address,place,nickname,weight_type,des,
                            at_school,sms_content,null,reward,null,from_weixin)
                    .compose(TransFormUtils.<HttpResultWithoutData>defaultSchedulers())
                    .subscribe(new HttpResultMsgSubscriber() {
                        @Override
                        public void onSuccessWithMsg(String result) {
                            Log.e(tag,result);
                            onPublishListener.onSuccess(result,Constant.SUCCESS_WITH_MSG);
                        }

                        @Override
                        public void _onError(Throwable throwable) {
                            Log.e(tag,throwable.getMessage());
                            onPublishListener.onError(throwable,Constant.FAILED_WITH_EXCEPTION);
                        }
                    });
        }
    }


}
