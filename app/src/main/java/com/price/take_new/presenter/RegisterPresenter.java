package com.price.take_new.presenter;

import android.text.TextUtils;

import com.price.take_new.Constant;
import com.price.take_new.model.RegisterModel;
import com.price.take_new.service.api.OnRegisterListener;
import com.price.take_new.service.modelService.IRegisterModel;
import com.price.take_new.service.viewService.IRegisterView;

/**
 * Created by price on 2/19/2017.
 */

public class RegisterPresenter {
    private IRegisterModel registerModel;
    private IRegisterView registerView;

    public RegisterPresenter(IRegisterView registerView) {
        this.registerModel = new RegisterModel();
        this.registerView = registerView;
    }

    public void register(String phone,String password){
        registerModel.register(phone, password, new OnRegisterListener() {
            @Override
            public void onSuccess(String msg, int hint) {
                registerView.showToast(msg,Constant.SUCCESS_BACK);
            }

            @Override
            public void onError(Throwable throwable, int error) {
                registerView.showToast(throwable.getMessage(),error);
            }
        });
    }

    public boolean check(String phone, String password, String confirm_password) {
        if(TextUtils.isEmpty(phone)){
            registerView.showToast("请输入手机号", Constant.FAILED_WITH_MSG);
            return false;
        }else if(TextUtils.isEmpty(password)){
            registerView.showToast("请输入密码",Constant.FAILED_WITH_MSG);
            return false;
        }else if(TextUtils.isEmpty(confirm_password)){
            registerView.showToast("请输入确认密码",Constant.FAILED_WITH_MSG);
            return false;
        }else if(!password.equals(confirm_password)){
            registerView.showToast("两次输入的密码不同，请重新确认",Constant.FAILED_WITH_MSG);
            return false;
        }
        return true;
    }
}
