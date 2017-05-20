package com.example.takeretrofit.model;

import android.util.Log;

import com.example.takeretrofit.api.AuthenticationApi;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultMsgSubscriber;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultWithoutData;
import com.example.takeretrofit.utils.TransFormUtils;


/**
 * Created by price on 1/14/2017.
 */

public class Authenticate {
    //认证
    private String tag = "Authenticate_test";
    public void authenticate(String token,String stu_id,String stu_password){
        ServiceFactory.getInstance().createService(AuthenticationApi.class)
                .authenticate(token,stu_id,stu_password)
                .compose(TransFormUtils.<HttpResultWithoutData>defaultSchedulers())
                .subscribe(new HttpResultMsgSubscriber() {
                    @Override
                    public void onSuccessWithMsg(String result) {
                        Log.e(tag,result);
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(tag,throwable.getMessage());
                    }
                });
    }
}
