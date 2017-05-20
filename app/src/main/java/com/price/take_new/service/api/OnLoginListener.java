package com.price.take_new.service.api;

import com.price.take_new.Constant;

/**
 * Created by price on 2/19/2017.
 */

public interface OnLoginListener<T> {
    void onSuccess(String token, String ry_token,int success);
    void onMsg(String msg,int hint);
    void onError(Throwable throwable,int error);
//    void onComplete(T result);
}
