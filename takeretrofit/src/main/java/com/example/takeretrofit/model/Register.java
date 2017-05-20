package com.example.takeretrofit.model;

import android.util.Log;

import com.example.takeretrofit.api.RegisterApi;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultMsgSubscriber;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultWithoutData;
import com.example.takeretrofit.utils.MD5Tool;
import com.example.takeretrofit.utils.TransFormUtils;


/**
 * Created by price on 1/14/2017.
 */

public class Register {
    //注册
    private String tag = "register_test";

    public void register(String phone_num,String password){
        ServiceFactory.getInstance().createService(RegisterApi.class)
                .register(phone_num, MD5Tool.md5(password))
                .compose(TransFormUtils.<HttpResultWithoutData>defaultSchedulers())
                .subscribe(new HttpResultMsgSubscriber() {
                    @Override
                    public void onSuccessWithMsg(String result) {
                        //网络请求成功时，包括注册成功或注册传入某参数出错等情况
                        Log.e(tag,result);
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        //网络请求失败，如网络不可用等异常出错
                        Log.e(tag,throwable.getMessage());
                    }
                });
    }
}
