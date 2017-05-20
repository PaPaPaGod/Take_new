package com.example.takeretrofit.model;

import android.util.Log;


import com.example.takeretrofit.api.GetSchoolListApi;
import com.example.takeretrofit.bean.schoollist.SchoolListDatum;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResultSubscriber;
import com.example.takeretrofit.utils.TransFormUtils;

import java.util.List;


/**
 * Created by price on 1/14/2017.
 */

public class GetSchoolList {
    //获取学校列表
    private static final String TAG = "get_school_list_test";
    public void getSchoolList(String token){
        ServiceFactory.getInstance().createService(GetSchoolListApi.class)
                .getSchoolList(token)
                .compose(TransFormUtils.<HttpResult<List<SchoolListDatum>>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<List<SchoolListDatum>>() {
                    @Override
                    public void onSuccessWithData(List<SchoolListDatum> schoolListData) {
                        Log.e(TAG,schoolListData.get(0).getName());
                    }

                    @Override
                    public void onSuccessWithMsg(HttpResult<List<SchoolListDatum>> httpResult) {
                        Log.e(TAG,httpResult.getMsg());
                    }

                    @Override
                    public void _onError(Throwable throwable) {

                    }
                });
    }
}
