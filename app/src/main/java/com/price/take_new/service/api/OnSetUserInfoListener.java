package com.price.take_new.service.api;

import com.price.take_new.bean.UserInfo;

/**
 * Created by price on 2/22/2017.
 */
public interface OnSetUserInfoListener {
    void onSuccess(String msg, String ry_token, int code);
    void onError(String msg, int code);
}
