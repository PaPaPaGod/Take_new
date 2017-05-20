package com.price.take_new.model;

import android.util.Log;

import com.example.takeretrofit.api.DeleteMyOrderApi;
import com.example.takeretrofit.api.UpdateMyOrderApi;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultMsgSubscriber;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultWithoutData;
import com.example.takeretrofit.utils.TransFormUtils;
import com.price.take_new.Constant;
import com.price.take_new.service.api.OnManagerExpressListener;
import com.price.take_new.service.modelService.IManagerExpressModel;

/**
 * Created by price on 2/24/2017.
 */

public class ManagerExpressModel implements IManagerExpressModel {

    private String tag = "manager_express_test";

    //删除已发布的订单
    @Override
    public void delete(String token, String order_id, final OnManagerExpressListener onManagerExpressListener) {
        ServiceFactory.getInstance().createService(DeleteMyOrderApi.class)
                .delete(token,order_id)
                .compose(TransFormUtils.<HttpResultWithoutData>defaultSchedulers())
                .subscribe(new HttpResultMsgSubscriber() {
                    @Override
                    public void onSuccessWithMsg(String result) {
                        Log.e(tag,result);
                        onManagerExpressListener.onSuccess(result, Constant.SUCCESS_WITH_MSG);
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(tag,throwable.getMessage());
                        onManagerExpressListener.onError(throwable.getMessage(),Constant.FAILED_WITH_EXCEPTION);
                    }
                });
    }

    //修改快递
    @Override
    public void update(String token, String order_id, String company, String des, String address, String place, String price, String take_time, final OnManagerExpressListener onManagerExpressListener) {
        ServiceFactory.getInstance().createService(UpdateMyOrderApi.class)
                .update(token,order_id,company,des,address,take_time,place,price)
                .compose(TransFormUtils.<HttpResultWithoutData>defaultSchedulers())
                .subscribe(new HttpResultMsgSubscriber() {
                    @Override
                    public void onSuccessWithMsg(String result) {
                        Log.e(tag,result);
                        onManagerExpressListener.onSuccess(result, Constant.SUCCESS_WITH_MSG);
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(tag,throwable.getMessage());
                        onManagerExpressListener.onError(throwable.getMessage(),Constant.FAILED_WITH_EXCEPTION);
                    }
                });
    }
}
