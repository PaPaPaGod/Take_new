package com.price.take_new.service.api;

/**
 * Created by price on 2/23/2017.
 */

public interface OnAuthListener {
    void onSuccess(String msg, int code);
    void onError(String msg, int code);
}
