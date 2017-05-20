package com.price.take_new.service.modelService;

import com.price.take_new.bean.UserInfo;
import com.price.take_new.service.api.OnSetUserInfoListener;

/**设置个人信息
 * Created by price on 2/22/2017.
 */

public interface ISetInfoModel {
    void set(String token,String name, String sex, String school_id, String major,OnSetUserInfoListener onSetUserInfoListener);
}
