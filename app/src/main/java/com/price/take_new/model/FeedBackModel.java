package com.price.take_new.model;

import android.util.Log;

import com.example.takeretrofit.api.SubmitAdviceApi;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultMsgSubscriber;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultWithoutData;
import com.example.takeretrofit.utils.TransFormUtils;
import com.price.take_new.Constant;
import com.price.take_new.service.api.OnFeedBackListener;
import com.price.take_new.service.modelService.IFeedBackModel;

/**
 * Created by price on 2/23/2017.
 */

public class FeedBackModel implements IFeedBackModel {
    private String tag = "SubmitAdvice_test";

    @Override
    public void feedBack(String token, String des, final OnFeedBackListener feedBackListener) {
        ServiceFactory.getInstance().createService(SubmitAdviceApi.class)
                .submit(token,des)
                .compose(TransFormUtils.<HttpResultWithoutData>defaultSchedulers())
                .subscribe(new HttpResultMsgSubscriber() {
                    @Override
                    public void onSuccessWithMsg(String result) {
                        Log.e(tag,result);
                        feedBackListener.onSuccess(result, Constant.SUCCESS_WITH_MSG);
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(tag,throwable.getMessage());
                        feedBackListener.onError(throwable.getMessage(),Constant.FAILED_WITH_EXCEPTION);
                    }
                });
    }
}
