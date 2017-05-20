package com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata;

import android.util.Log;


import com.example.takeretrofit.Config;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by price on 1/15/2017.
 */

public abstract class HttpResultMsgSubscriber extends Subscriber<HttpResultWithoutData> {

    private String tag = "HttpResultMsgSubscriber";

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Log.e(tag,e.getMessage());
        e.printStackTrace();
        //在这里做全局的错误处理
        if (e instanceof HttpException) {
            // ToastUtils.getInstance().showToast(e.getMessage());
        }
        _onError(e);
    }

    @Override
    public void onNext(HttpResultWithoutData data) {
        int code = data.getCode();
        Log.e(tag,code+"");
//        code = 0;
        switch (code){
            case Config.SUCCESS_WITH_STATUS:
                onSuccessWithMsg(data.getMsg());
                break;
            case Config.FAILED_WITH_STATUS:
                _onError(new Throwable("error: "+data.getMsg()));
                break;
            case Config.TOKEN_INVALED:
                _onError(new Throwable("error: "+data.getMsg()));
                break;
        }
    }

    public abstract void onSuccessWithMsg(String result);

    public abstract void _onError(Throwable throwable);
}
