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
//    private String tag = "CreateDevilery_test";
//    public void createDevilery(String token, String company, String des, String address,
//                               String place,int from_weixin, String nickname,
//                               int weight_type,int at_school,String sms_content,String reward,boolean moneyType){
//        if(moneyType){
//            ServiceFactory.getInstance().createService(CreateDevileryApi.class)
//                    .createOrder(token,company,address,place,nickname,weight_type,des,
//                            at_school,sms_content,reward,null,null,from_weixin)
//                    .compose(TransFormUtils.<HttpResultWithoutData>defaultSchedulers())
//                    .subscribe(new HttpResultMsgSubscriber() {
//                        @Override
//                        public void onSuccessWithMsg(String result) {
//                            Log.e(tag,result);
//                        }
//
//                        @Override
//                        public void _onError(Throwable throwable) {
//                            Log.e(tag,throwable.getMessage());
//                        }
//                    });
//        }else {
//            ServiceFactory.getInstance().createService(CreateDevileryApi.class)
//                    .createOrder(token,company,address,place,nickname,weight_type,des,
//                            at_school,sms_content,null,reward,null,from_weixin)
//                    .compose(TransFormUtils.<HttpResultWithoutData>defaultSchedulers())
//                    .subscribe(new HttpResultMsgSubscriber() {
//                        @Override
//                        public void onSuccessWithMsg(String result) {
//                            Log.e(tag,result);
//                        }
//
//                        @Override
//                        public void _onError(Throwable throwable) {
//                            Log.e(tag,throwable.getMessage());
//                        }
//                    });
//        }
//
//    }
}
