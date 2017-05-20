package com.price.take_new.model;

import android.util.Log;

import com.example.takeretrofit.api.UpdateUserInfoApi;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResultSubscriber;
import com.example.takeretrofit.utils.TransFormUtils;
import com.price.take_new.Constant;
import com.price.take_new.service.api.OnSetUserInfoListener;
import com.price.take_new.service.modelService.ISetInfoModel;

/**
 * Created by price on 2/23/2017.
 */

public class SetUserInfoModel implements ISetInfoModel {

    private String tag = "update_user_info";

    @Override
    public void set(String token, String name, String sex, String school_id,
                    String major, final OnSetUserInfoListener onSetUserInfoListener) {
        ServiceFactory.getInstance().createService(UpdateUserInfoApi.class)
                .updateUserInfo(token,name,sex,school_id,major)
                .compose(TransFormUtils.<HttpResult<String>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<String>() {
                    @Override
                    public void onSuccessWithData(String data) {
                        Log.e(tag,data);
                        onSetUserInfoListener.onSuccess("设置完成",data, Constant.SUCCESS_WITH_DATA);
                    }

                    @Override
                    public void onSuccessWithMsg(HttpResult<String> httpResult) {
                        Log.e(tag,httpResult.getMsg());
                        onSetUserInfoListener.onSuccess(httpResult.getMsg(),"",
                                Constant.FAILED_WITH_MSG);
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(tag,throwable.getMessage());
                        onSetUserInfoListener.onError(throwable.getMessage(),Constant.FAILED_WITH_EXCEPTION);
                    }
                });
    }
}
