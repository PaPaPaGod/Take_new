package com.example.takeretrofit.model;

import android.app.Service;
import android.util.Log;

import com.example.takeretrofit.api.DeliveryApi;
import com.example.takeretrofit.api.GetFriendListApi;
import com.example.takeretrofit.bean.friend.FriendDatum;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResultSubscriber;
import com.example.takeretrofit.utils.TransFormUtils;

import java.util.List;

/**
 * Created by intel on 3/15/2018.
 */

public class GetFriendList {
    //获取朋友列表
    private static final String TAG = "GetFriendList_test";
    public void getFriendList(String token,String page,String size){
        ServiceFactory.getInstance().createService(GetFriendListApi.class)
                .getFriendList(token, page,size)
                .compose(TransFormUtils.<HttpResult<List<FriendDatum>>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<List<FriendDatum>>() {
                    @Override
                    public void onSuccessWithData(List<FriendDatum> friendData) {
//                        Log.e(TAG,friendData.get(0).get());
                    }

                    @Override
                    public void onSuccessWithMsg(HttpResult<List<FriendDatum>> httpResult) {
                        Log.e(TAG,httpResult.getMsg());
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(TAG,throwable.getMessage());
                    }
                });
    }

}
