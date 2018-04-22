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
    private static final String TAG = "delivery";
    public void getDelivery(String token,int page,String filter){
        ServiceFactory.getInstance().createService(DeliveryApi.class)
                .getDelivery(token, page,filter)
                .compose(TransFormUtils.<HttpResult<List<DeliveryDatum>>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<List<DeliveryDatum>>() {
                    @Override
                    public void onSuccessWithData(List<DeliveryDatum> deliveryData) {
                        Log.e(TAG,"onSuccessWithData");
                        if(deliveryData==null){
                            Log.e(TAG,"delivery is null");
                        }
                        Log.e(TAG,deliveryData.size()+"size");
                    }

                    @Override
                    public void onSuccessWithMsg(HttpResult<List<DeliveryDatum>> httpResult) {
                        Log.e(TAG,"onSuccessWithMsg");
                        if(httpResult==null){
                            Log.e(TAG,"httpResult is null");
                        }
                        Log.e(TAG,httpResult.getData().get(0).getId());
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(TAG,"_onError");
                        if(throwable==null){
                            Log.e(TAG,"throwable is null");
                        }
                        Log.e(TAG,throwable.getMessage());
                    }
                });
    }
}
