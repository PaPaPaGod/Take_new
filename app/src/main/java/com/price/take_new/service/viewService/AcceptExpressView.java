package com.price.take_new.service.viewService;

import android.os.Bundle;

/**
 * 接受快递
 * Created by price on 2/21/2017.
 */

public interface AcceptExpressView {
    void showData(Bundle data);
    void showToast(String msg, int code);
    void showMyDialog(int code);
}
