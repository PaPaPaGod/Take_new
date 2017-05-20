package com.price.take_new.model;

import android.util.Log;

import com.example.takeretrofit.api.RegisterApi;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultMsgSubscriber;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultWithoutData;
import com.example.takeretrofit.utils.MD5Tool;
import com.example.takeretrofit.utils.TransFormUtils;
import com.price.take_new.Constant;
import com.price.take_new.service.api.OnRegisterListener;
import com.price.take_new.service.modelService.IRegisterModel;

/**
 * Created by price on 2/19/2017.
 */

public class RegisterModel implements IRegisterModel {
    private String tag = "register_test";

    @Override
    public void register(String phone, String password, final OnRegisterListener onRegisterListener) {
        ServiceFactory.getInstance().createService(RegisterApi.class)
                .register(phone, MD5Tool.md5(password))
                .compose(TransFormUtils.<HttpResultWithoutData>defaultSchedulers())
                .subscribe(new HttpResultMsgSubscriber() {
                    @Override
                    public void onSuccessWithMsg(String result) {
                        //网络请求成功时，包括注册成功或注册传入某参数出错等情况
                        Log.e(tag,result);
                        onRegisterListener.onSuccess(result, Constant.FAILED_WITH_MSG);
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        //网络请求失败，如网络不可用等异常出错
                        Log.e(tag,throwable.getMessage());
                        onRegisterListener.onError(throwable,Constant.FAILED_WITH_EXCEPTION);
                    }
                });
    }
}
