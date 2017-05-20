package com.price.take_new.service.modelService;

import android.content.Context;

import com.example.takeretrofit.bean.userinfo.UserInfoData;
import com.price.take_new.service.api.OnLoginListener;

/**
 * Created by price on 2/18/2017.
 */

public interface ILoginModel {
//    String getPhone(Context context);

    void login(String phone_num, String password, OnLoginListener onLoginListener);

    void getUserInfo(String token);
//    UserInfoData load();

//    String getToken();

//    String getMsg();
}
