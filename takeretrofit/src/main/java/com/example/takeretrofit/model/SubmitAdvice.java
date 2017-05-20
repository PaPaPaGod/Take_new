package com.example.takeretrofit.model;

import android.util.Log;

import com.example.takeretrofit.api.SubmitAdviceApi;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultMsgSubscriber;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultWithoutData;
import com.example.takeretrofit.utils.TransFormUtils;


/**
 * Created by price on 1/14/2017.
 */

public class SubmitAdvice {
    private String tag = "SubmitAdvice_test";
    public void submitAdvice(String token,String des){
        ServiceFactory.getInstance().createService(SubmitAdviceApi.class)
                .submit(token,des)
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
