package com.example.takeretrofit.model;

import android.util.Log;

import com.example.takeretrofit.api.GetOtherUserInfoApi;
import com.example.takeretrofit.bean.otheruserinfo.OtherUserInfoData;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResultSubscriber;
import com.example.takeretrofit.utils.TransFormUtils;


/**
 * Created by price on 1/15/2017.
 */

public class GetOtherUserInfo {
    //获取他人信息
    private String tag = "GetOtherUserInfo_test";
    public void getOtherUserInfo(String token,String user_id){
        ServiceFactory.getInstance().createService(GetOtherUserInfoApi.class)
                .getOtherUserInfo(token,user_id)
                .compose(TransFormUtils.<HttpResult<OtherUserInfoData>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<OtherUserInfoData>() {
                    @Override
                    public void onSuccessWithData(OtherUserInfoData otherUserInfoData) {
                        Log.e(tag,otherUserInfoData.getPhoneNum());
                    }

                    @Override
                    public void onSuccessWithMsg(HttpResult<OtherUserInfoData> httpResult) {
                        Log.e(tag,httpResult.getMsg());
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(tag,"onError: "+throwable.getMessage());
                    }
                });
    }
}
