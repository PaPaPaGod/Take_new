package com.price.take_new.service.modelService;

import com.price.take_new.bean.UserInfo;
import com.price.take_new.service.api.AcceptExpressListener;

/**
 * 接受快递
 * Created by price on 2/21/2017.
 */

public interface IAcceptExpressModel {
    void getExpress(String token, String poster_id, String express_id, UserInfo info,AcceptExpressListener acceptExpressListener);
}
