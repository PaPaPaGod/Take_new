package com.example.takeretrofit.model;

import android.util.Log;


import com.example.takeretrofit.api.GetMyHelpDeliveryApi;
import com.example.takeretrofit.bean.myhelpdeliveryorder.MyHelpOrderDatum;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResultSubscriber;
import com.example.takeretrofit.utils.TransFormUtils;

import java.util.List;

/**
 * Created by price on 1/15/2017.
 */

public class GetMyHelpOrder {
    //我代拿的快递
    private String tag="getMyHelpOrder";
    public void getMyHelpOrder(String token,String page){
        ServiceFactory.getInstance().createService(GetMyHelpDeliveryApi.class)
                .getMyHelpOrder(token, page)
                .compose(TransFormUtils.<HttpResult<List<MyHelpOrderDatum>>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<List<MyHelpOrderDatum>>() {
                    @Override
                    public void onSuccessWithData(List<MyHelpOrderDatum> myHelpOrderData) {
                        Log.e(tag,myHelpOrderData.get(0).getName());
                    }

                    @Override
                    public void onSuccessWithMsg(HttpResult<List<MyHelpOrderDatum>> httpResult) {
                        Log.e(tag,httpResult.getMsg());
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(tag,throwable.getMessage());
                    }
                });
    }
}
