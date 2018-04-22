package com.price.take_new.service.modelService;

import com.price.take_new.bean.UserInfo;
import com.price.take_new.service.api.AcceptExpressListener;

/**
 * 接受快递
 * Created by price on 2/21/2017.
 */

public interface IAcceptExpressModel {
    void getExpress(String token, String express_id, AcceptExpressListener acceptExpressListener);
}
