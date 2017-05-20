package com.price.take_new.service.modelService;

import com.price.take_new.service.api.OnAuthListener;

/**
 * Created by price on 2/23/2017.
 */

public interface IAuthModel {
    void auth(String token, String stu_id, String password, OnAuthListener authListener);
}
