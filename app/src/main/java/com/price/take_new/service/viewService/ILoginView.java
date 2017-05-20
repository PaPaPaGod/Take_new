package com.price.take_new.service.viewService;

import com.price.take_new.bean.UserInfo;

/**
 * Created by price on 2/18/2017.
 */

public interface ILoginView {
    String getPhoneNum();
    String getPassword();
    void setPhoneNum(String phoneNum);
    void setPassword(String password);
    void showToast(String msg);
    void goToHome(String token, String ry_token);
    void showLoading();
    void hideLoading();

    void goToSetInfo(String token);
}
