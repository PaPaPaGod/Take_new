package com.example.takeretrofit.model;

import android.util.Log;

import com.example.takeretrofit.api.GetUserInfoApi;
import com.example.takeretrofit.bean.userinfo.UserInfoData;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResultSubscriber;
import com.example.takeretrofit.utils.TransFormUtils;


/**
 * Created by price on 1/14/2017.
 */

public class GetUserInfo {
    //获取个人信息
    String tag = "getuserinfo";

    public void getUserInfo(String token){
        ServiceFactory.getInstance().createService(GetUserInfoApi.class)
                .getUserInfo(token)
                .compose(TransFormUtils.<HttpResult<UserInfoData>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<UserInfoData>() {
                    @Override
                    public void onSuccessWithData(UserInfoData userInfoData) {
//                        Log.e(tag, userInfoData.getAuth());
                    }

                    @Override
                    public void onSuccessWithMsg(HttpResult<UserInfoData> httpResult) {
                        Log.e(tag,httpResult.getMsg());
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(tag,throwable.getMessage());
                    }
                });
    }
}
