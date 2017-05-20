package com.price.take_new.service.modelService;

import com.price.take_new.service.api.OnFeedBackListener;

/**
 * Created by price on 2/23/2017.
 */

public interface IFeedBackModel {
    void feedBack(String token, String des, OnFeedBackListener feedBackListener);
}
