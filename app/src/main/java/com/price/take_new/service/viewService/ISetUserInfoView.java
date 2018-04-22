package com.price.take_new.service.viewService;

import com.price.take_new.bean.UserInfo;

/**
 * Created by price on 2/22/2017.
 */

public interface ISetUserInfoView {
    void bindRy_token(String ry_token);
    void showToast(String msg, int code);
}
