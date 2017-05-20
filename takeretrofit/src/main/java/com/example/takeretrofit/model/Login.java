package com.example.takeretrofit.model;

import android.util.Log;

import com.example.takeretrofit.api.LoginApi;
import com.example.takeretrofit.bean.login.LoginData;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResultSubscriber;
import com.example.takeretrofit.utils.MD5Tool;
import com.example.takeretrofit.utils.TransFormUtils;


/**
 * Created by price on 1/12/2017.
 */

public class Login {
    //登陆
    private static final String TAG = "login_test";

    private String token = null;
//    private String error = null;
    private String msg = null;

    public String getToken() {
        return token;
    }

    public String getMsg() {
        return msg;
    }

    public void login(String phone_num, String password){
        ServiceFactory.getInstance().createService(LoginApi.class)
                .login(phone_num, MD5Tool.md5(password))
                .compose(TransFormUtils.<HttpResult<LoginData>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<LoginData>() {
                    @Override
                    public void onSuccessWithData(LoginData loginData) {
                        //网络请求成功时调用，如登陆成功等情况
                        Log.e(TAG, loginData.getToken());
                        token = loginData.getToken();
//                        ManagerData.cacheToken();
                    }

                    @Override
                    public void onSuccessWithMsg(HttpResult<LoginData> httpResult) {
                        //网络请求成功时调用,但并没有数据返回（如账号密码错误）
                        //此时只有错误信息返回
                        Log.e(TAG, httpResult.getMsg());
                        msg = httpResult.getMsg();
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        //网络请求失败时调用，如网络不可用等异常情况
                        msg = throwable.getMessage();
                    }
                });
    }
}
