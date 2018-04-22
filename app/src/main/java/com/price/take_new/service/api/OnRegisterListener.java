package com.price.take_new.service.api;

/**
 * Created by price on 2/19/2017.
 */

public interface OnRegisterListener {
    public abstract void onSuccess(String msg, int hint);
    public abstract void onError(Throwable throwable, int error);
}
