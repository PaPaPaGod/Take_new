package com.price.take_new.service.viewService;

import com.example.takeretrofit.bean.otheruserinfo.OtherUserInfoData;
import com.price.take_new.bean.UserInfo;

/**
 * Created by price on 2/25/2017.
 */

public interface IGetOtherUserInfoView {
    void onBind(OtherUserInfoData info);
    void showToast(String msg);
}
