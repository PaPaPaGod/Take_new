package com.price.take_new.model;

import android.util.Log;

import com.example.takeretrofit.api.GetMyDeliveryOrderApi;
import com.example.takeretrofit.api.GetMyHelpDeliveryApi;
import com.example.takeretrofit.bean.mydeliveryorder.MyDeliveryOrderDatum;
import com.example.takeretrofit.bean.myhelpdeliveryorder.MyHelpOrderDatum;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResultSubscriber;
import com.example.takeretrofit.utils.TransFormUtils;
import com.price.take_new.Constant;
import com.price.take_new.service.api.OnMyExpressListener;
import com.price.take_new.service.api.OnMyHelpExpressListener;
import com.price.take_new.service.api.OnMyPublishExpressListener;
import com.price.take_new.service.modelService.IMyExpressModel;

import java.util.List;

/**代拿快递与我的快递
 * Created by price on 2/23/2017.
 */

public class MyExpressModel implements IMyExpressModel {

    private String tag="getMyOrder";

    //我的快递
    @Override
    public void getExpress(String token, String page, final OnMyPublishExpressListener onMyExpressListener) {
        ServiceFactory.getInstance().createService(GetMyDeliveryOrderApi.class)
                .getMyPostOrder(token,page)
                .compose(TransFormUtils.<HttpResult<List<MyDeliveryOrderDatum>>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<List<MyDeliveryOrderDatum>>() {
                    @Override
                    public void onSuccessWithData(List<MyDeliveryOrderDatum> myDeliveryOrderData) {
//                        if(myDeliveryOrderData.get(0).getSms_content().equals("")){
//                            Log.e("sms_content","is null");
//                        }else{
//                            Log.e("sms_content","is not null");
//                        }
                        onMyExpressListener.onMySuccess(myDeliveryOrderData,Constant.SUCCESS_WITH_DATA);
                    }

                    @Override
                    public void onSuccessWithMsg(HttpResult<List<MyDeliveryOrderDatum>> httpResult) {
                        Log.e(tag,httpResult.getMsg());
                        onMyExpressListener.onMsg(httpResult.getMsg(),Constant.FAILED_WITH_MSG);
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(tag,throwable.getMessage());
                        onMyExpressListener.onError(throwable.getMessage(),Constant.FAILED_WITH_EXCEPTION);
                    }
                });
    }

    //代拿快递
    @Override
    public void getHelpExpress(String token, String page, final OnMyHelpExpressListener onMyExpressListener) {
        ServiceFactory.getInstance().createService(GetMyHelpDeliveryApi.class)
                .getMyHelpOrder(token, page)
                .compose(TransFormUtils.<HttpResult<List<MyHelpOrderDatum>>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<List<MyHelpOrderDatum>>() {
                    @Override
                    public void onSuccessWithData(List<MyHelpOrderDatum> myHelpOrderData) {
                        Log.e(tag,myHelpOrderData.get(0).getName());
                        onMyExpressListener.onHelpSuccess(myHelpOrderData,Constant.SUCCESS_WITH_DATA);
                    }

                    public void onSuccessWithMsg(HttpResult<List<MyHelpOrderDatum>> httpResult) {
                        Log.e(tag,httpResult.getMsg());
                        onMyExpressListener.onMsg(httpResult.getMsg(),Constant.FAILED_WITH_MSG);
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(tag,throwable.getMessage());
                        onMyExpressListener.onError(throwable.getMessage(),Constant.FAILED_WITH_EXCEPTION);
                    }
                });
    }
}
