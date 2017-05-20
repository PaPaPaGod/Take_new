package com.price.take_new.service.viewService;

import com.price.take_new.bean.UserInfo;

/**
 * Created by price on 2/24/2017.
 */

public interface IGetUserInfoView {
    void showToast(String msg);
    void bindData(UserInfo info);
    void hideLoading();
    void showLoading();
}
