package com.example.takeretrofit.model;

import android.util.Log;

import com.example.takeretrofit.api.UpdateUserInfoApi;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResultSubscriber;
import com.example.takeretrofit.utils.TransFormUtils;


/**
 * Created by price on 1/14/2017.
 */

public class UpdateUserInfo {
    private String tag = "update_user_info";
    public void updateUserInfo(String token, String name, String sex, String school_id, String major){
        ServiceFactory.getInstance().createService(UpdateUserInfoApi.class)
                .updateUserInfo(token,name,sex,school_id,major)
                .compose(TransFormUtils.<HttpResult<String>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<String>() {
                    @Override
                    public void onSuccessWithData(String data) {
                        Log.e(tag,data);
                    }

                    @Override
                    public void onSuccessWithMsg(HttpResult<String> httpResult) {
                        Log.e(tag,httpResult.getMsg());
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(tag,throwable.getMessage());
                    }
                });
    }
}
