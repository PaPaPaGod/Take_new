package com.price.take_new.service.modelService;

import com.price.take_new.service.api.OnExpressListener;

/**
 * Created by price on 2/20/2017.
 */

public interface IGetExpressModel {
    void getExpress(String token, int page, String filter, OnExpressListener onExpressListener);
}
