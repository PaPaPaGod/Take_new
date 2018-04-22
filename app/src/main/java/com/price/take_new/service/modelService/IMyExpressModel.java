package com.price.take_new.service.modelService;

import com.price.take_new.service.api.OnMyExpressListener;
import com.price.take_new.service.api.OnMyHelpExpressListener;
import com.price.take_new.service.api.OnMyPublishExpressListener;

/**代拿快递与我的快递
 * Created by price on 2/23/2017.
 */

public interface IMyExpressModel {
    void getExpress(String token, String page, OnMyPublishExpressListener onMyExpressListener);
    void getHelpExpress(String token, String page, OnMyHelpExpressListener onMyExpressListener);
}
