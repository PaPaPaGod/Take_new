package com.price.take_new.service.modelService;

import com.price.take_new.service.api.OnGetOtherUserInfoListener;

/**
 * Created by price on 2/25/2017.
 */

public interface IGetOtherUserInfo {
    void getOtherInfo(String token, String poster_id, OnGetOtherUserInfoListener listener);
}
