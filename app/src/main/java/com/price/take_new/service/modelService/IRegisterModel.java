package com.price.take_new.service.modelService;

import com.price.take_new.service.api.OnRegisterListener;

/**
 * Created by price on 2/19/2017.
 */

public interface IRegisterModel {
    void register(String phone, String password, OnRegisterListener onRegisterListener);
}
