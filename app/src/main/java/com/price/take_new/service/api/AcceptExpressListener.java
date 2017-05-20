package com.price.take_new.service.api;

/**
 * 接受快递
 * Created by price on 2/21/2017.
 */

public interface AcceptExpressListener {
    void onSuccess(String msg,int code);
    void onError(String msg,int code);
}
