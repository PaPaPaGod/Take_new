package com.price.take_new.presenter;

import com.price.take_new.model.AuthModel;
import com.price.take_new.service.api.OnAuthListener;
import com.price.take_new.service.modelService.IAuthModel;
import com.price.take_new.service.viewService.IAuthView;

/**
 * Created by price on 2/23/2017.
 */

public class AuthPresenter {
    private IAuthModel authModel;

    private IAuthView authView;

    public AuthPresenter(IAuthView authView) {
        this.authView = authView;
        this.authModel = new AuthModel();
    }

    public void auth(String token,String stu_id,String password){
        authModel.auth(token, stu_id, password, new OnAuthListener() {
            @Override
            public void onSuccess(String msg, int code) {
                authView.showToast(msg,code);
            }

            @Override
            public void onError(String msg, int code) {
                authView.showToast(msg,code);
            }
        });
    }

}
