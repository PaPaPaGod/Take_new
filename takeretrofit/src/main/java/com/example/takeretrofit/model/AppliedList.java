package com.example.takeretrofit.model;

import android.util.Log;

import com.example.takeretrofit.api.AppliedFriendListApi;
import com.example.takeretrofit.api.DeliveryApi;
import com.example.takeretrofit.bean.appliedlist.AppliedListData;
import com.example.takeretrofit.bean.delivery.DeliveryDatum;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResultSubscriber;
import com.example.takeretrofit.utils.TransFormUtils;

import java.util.List;

/**
 * Created by price on 1/13/2017.
 */

public class AppliedList {
    //获取快递列表
    private static final String TAG = "appliedlist";
    public void getAppliedList(String token,String page,String size){
        ServiceFactory.getInstance().createService(AppliedFriendListApi.class)
                .getAppliedList(token, page,size)
                .compose(TransFormUtils.<HttpResult<List<AppliedListData>>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<List<AppliedListData>>() {
                    @Override
                    public void onSuccessWithData(List<AppliedListData> listData) {
                        Log.e(TAG,"onSuccessWithData");
                        if(listData==null){
                            Log.e(TAG,"listData is null");
                        }
                        Log.e(TAG,listData.size()+"size");
                    }

                    @Override
                    public void onSuccessWithMsg(HttpResult<List<AppliedListData>> httpResult) {
                        Log.e(TAG,"onSuccessWithMsg");
                        if(httpResult==null){
                            Log.e(TAG,"httpResult is null");
                        }
                        Log.e(TAG,httpResult.getData().get(0).getStu_id());
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(TAG,"_onError");
                        if(throwable==null){
                            Log.e(TAG,"throwable is null");
                        }
                        Log.e(TAG,throwable.getMessage());
                    }
                });
    }
}
