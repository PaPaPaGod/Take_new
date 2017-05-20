package com.price.take_new.model;

import android.util.Log;

import com.example.takeretrofit.api.AuthenticationApi;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultMsgSubscriber;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultWithoutData;
import com.example.takeretrofit.utils.TransFormUtils;
import com.price.take_new.Constant;
import com.price.take_new.service.api.OnAuthListener;
import com.price.take_new.service.modelService.IAuthModel;

/**
 * Created by price on 2/23/2017.
 */

public class AuthModel implements IAuthModel {

    //认证
    private String tag = "Authenticate_test";

    @Override
    public void auth(String token, String stu_id, String password, final OnAuthListener authListener) {
        ServiceFactory.getInstance().createService(AuthenticationApi.class)
                .authenticate(token,stu_id,password)
                .compose(TransFormUtils.<HttpResultWithoutData>defaultSchedulers())
                .subscribe(new HttpResultMsgSubscriber() {
                    @Override
                    public void onSuccessWithMsg(String result) {
                        Log.e(tag,result);
                        authListener.onSuccess(result, Constant.SUCCESS_WITH_MSG);
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(tag,throwable.getMessage());
                        authListener.onSuccess(throwable.getMessage(),Constant.FAILED_WITH_EXCEPTION);
                    }
                });
    }
}
