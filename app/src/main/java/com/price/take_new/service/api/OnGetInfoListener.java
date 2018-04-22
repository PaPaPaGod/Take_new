package com.price.take_new.service.api;

import com.example.takeretrofit.bean.userinfo.UserInfoData;

/**
 * Created by price on 2/22/2017.
 */

public interface OnGetInfoListener {
    void onSuccess(UserInfoData data, int code);
    void onMsg(String msg, int code);
    void onError(String msg, int code);
}
