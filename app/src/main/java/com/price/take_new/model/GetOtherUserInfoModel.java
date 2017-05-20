package com.price.take_new.model;

import android.util.Log;

import com.example.takeretrofit.api.GetOtherUserInfoApi;
import com.example.takeretrofit.bean.otheruserinfo.OtherUserInfoData;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResultSubscriber;
import com.example.takeretrofit.utils.TransFormUtils;
import com.price.take_new.service.api.OnGetOtherUserInfoListener;
import com.price.take_new.service.modelService.IGetOtherUserInfo;

/**
 * Created by price on 2/25/2017.
 */

public class GetOtherUserInfoModel implements IGetOtherUserInfo {
    //获取他人信息
    private String tag = "GetOtherUserInfo_test";

    @Override
    public void getOtherInfo(String token, String poster_id, final OnGetOtherUserInfoListener listener) {
        ServiceFactory.getInstance().createService(GetOtherUserInfoApi.class)
                .getOtherUserInfo(token,poster_id)
                .compose(TransFormUtils.<HttpResult<OtherUserInfoData>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<OtherUserInfoData>() {
                    @Override
                    public void onSuccessWithData(OtherUserInfoData otherUserInfoData) {
                        Log.e(tag,otherUserInfoData.getPhoneNum());
                        listener.onSuccess(otherUserInfoData);
                    }

                    @Override
                    public void onSuccessWithMsg(HttpResult<OtherUserInfoData> httpResult) {
                        Log.e(tag,httpResult.getMsg());
                        listener.onMsg(httpResult.getMsg());
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(tag,"onError: "+throwable.getMessage());
                        listener.onError(throwable.getMessage());
                    }
                });
    }
}
