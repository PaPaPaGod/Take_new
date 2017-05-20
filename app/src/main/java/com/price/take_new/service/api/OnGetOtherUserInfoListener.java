package com.price.take_new.service.api;

import com.example.takeretrofit.bean.otheruserinfo.OtherUserInfoData;
import com.price.take_new.bean.UserInfo;

/**
 * Created by price on 2/25/2017.
 */

public interface OnGetOtherUserInfoListener {
    void onSuccess(OtherUserInfoData info);
    void onMsg(String msg);
    void onError(String msg);
}
