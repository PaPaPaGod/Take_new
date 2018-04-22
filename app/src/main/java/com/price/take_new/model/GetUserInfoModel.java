package com.price.take_new.model;

import android.util.Log;

import com.example.takeretrofit.api.GetUserInfoApi;
import com.example.takeretrofit.bean.userinfo.UserInfoData;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResultSubscriber;
import com.example.takeretrofit.utils.TransFormUtils;
import com.price.take_new.Constant;
import com.price.take_new.service.api.OnGetInfoListener;
import com.price.take_new.service.modelService.IGetUserInfoModel;

import java.util.List;

/**
 * Created by price on 2/22/2017.
 */

public class GetUserInfoModel implements IGetUserInfoModel {

    //获取个人信息
    String tag = "getuserinfo";

    //联网获取
    @Override
    public void getInfo(String token, final OnGetInfoListener onGetInfoListener) {
        ServiceFactory.getInstance().createService(GetUserInfoApi.class)
                .getUserInfo(token)
                .compose(TransFormUtils.<HttpResult<UserInfoData>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<UserInfoData>() {
                    @Override
                    public void onSuccessWithData(UserInfoData userInfoData) {
                        Log.e(tag,"getmyinfo success");
                        onGetInfoListener.onSuccess(userInfoData, Constant.SUCCESS_WITH_DATA);
                    }

                    @Override
                    public void onSuccessWithMsg(HttpResult<UserInfoData> httpResult) {
                        Log.e(tag+"msg",httpResult.getMsg());
                        onGetInfoListener.onMsg(httpResult.getMsg(),Constant.SUCCESS_WITH_MSG);
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(tag+"error",throwable.getMessage());
                        onGetInfoListener.onError(throwable.getMessage(),Constant.FAILED_WITH_EXCEPTION);
                    }
                });
    }

}
