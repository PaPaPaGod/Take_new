package com.price.take_new.service.api;

/**
 * Created by price on 2/21/2017.
 */

public interface OnPublishListener {
    void onSuccess(String msg,int code);
    void onError(Throwable throwable,int code);
}
