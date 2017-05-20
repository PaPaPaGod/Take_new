package com.price.take_new.model;

import android.content.Context;
import android.util.Log;

import com.example.takeretrofit.api.LoginApi;
import com.example.takeretrofit.bean.login.LoginData;
import com.example.takeretrofit.bean.userinfo.UserInfoData;
import com.example.takeretrofit.model.Login;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResultSubscriber;
import com.example.takeretrofit.utils.MD5Tool;
import com.example.takeretrofit.utils.ManagerData;
import com.example.takeretrofit.utils.TransFormUtils;
import com.price.take_new.Constant;
import com.price.take_new.service.api.OnLoginListener;
import com.price.take_new.service.modelService.ILoginModel;

/**
 * Created by price on 2/18/2017.
 */

public class LoginModel implements ILoginModel {
    private Login loginApi;
    private String token = "token";
    private String msg = "msg";

//    @Override
//    public String getPhone(Context context) {
//        String phone = ManagerData.getPhoneNum(context);
//        if(!phone.isEmpty()){
//            return phone;
//        }
//        return null;
//    }

    @Override
    public void login(String phone_num, String password, final OnLoginListener onLoginListener) {
        ServiceFactory.getInstance().createService(LoginApi.class)
                .login(phone_num, MD5Tool.md5(password))
                .compose(TransFormUtils.<HttpResult<LoginData>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<LoginData>() {
                    @Override
                    public void onSuccessWithData(LoginData loginData) {
                        //网络请求成功时调用，如登陆成功等情况
//                        Log.e(TAG, loginData.getToken());
//                        data.setData(T);
                        token = loginData.getToken();
                        String ry_token = loginData.getRyToken();
                        onLoginListener.onSuccess(token, ry_token,Constant.SUCCESS_WITH_DATA);
//                        ManagerData.cacheToken();
                    }

                    @Override
                    public void onSuccessWithMsg(HttpResult<LoginData> httpResult) {
                        //网络请求成功时调用,但并没有数据返回（如账号密码错误）
                        //此时只有错误信息返回
//                        Log.e(TAG, httpResult.getMsg());
                        msg = httpResult.getMsg();
                        onLoginListener.onMsg(msg,Constant.SUCCESS_WITH_MSG);
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        //网络请求失败时调用，如网络不可用等异常情况
                        msg = throwable.getMessage();
                        onLoginListener.onError(throwable,Constant.FAILED_WITH_EXCEPTION);
                    }
                });
    }

    @Override
    public void getUserInfo(String token) {

    }


    public void login(String phone_num,String password) {

    }

}
