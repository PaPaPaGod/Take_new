package com.price.take_new.service.api;

/**
 * Created by intel on 3/29/2018.
 */

public interface DeleteFriendListener {
    void onSuccess(String msg, int code);
    void onError(String msg, int code);
}
