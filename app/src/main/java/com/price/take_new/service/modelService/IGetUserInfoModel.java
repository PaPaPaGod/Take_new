package com.price.take_new.service.modelService;

import com.example.takeretrofit.bean.userinfo.UserInfoData;
import com.price.take_new.bean.UserInfo;
import com.price.take_new.service.api.OnGetInfoListener;

/**获得个人信息
 * Created by price on 2/22/2017.
 */

public interface IGetUserInfoModel {
    void getInfo(String token, OnGetInfoListener onGetInfoListener);
}
