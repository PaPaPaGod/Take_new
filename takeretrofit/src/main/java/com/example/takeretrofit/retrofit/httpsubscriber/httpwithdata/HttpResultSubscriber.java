package com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata;

import android.util.Log;


import com.example.takeretrofit.Config;
import com.example.takeretrofit.utils.ToastUtils;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by price on 1/13/2017.
 */

public abstract class HttpResultSubscriber<T> extends Subscriber<HttpResult<T>> {

    private String tag = "HttpResultSubscriber";
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
//        Log.e(tag,e.getCause().getMessage());
//        Log.e("eee",);
        e.printStackTrace();
        //在这里做全局的错误处理
        Throwable throwable = new Throwable("网络不可用，请稍候重试");
        if (e instanceof SocketTimeoutException) {
            Log.e("http",e.getMessage());
            _onError(throwable);
//             ToastUtils.getInstance().showToast(e.getMessage());
        }else if(e instanceof UnknownHostException){
            Log.e("http",e.getMessage());
            _onError(throwable);
        }else{
            _onError(e);
        }
    }

    @Override
    public void onNext(HttpResult<T> t) {
        int code = t.getCode();
        String msg = null;
        msg = t.getMsg();
        Log.e(tag,code+"");
        if(msg!=null){
            Log.e("t.msg",msg);
        }
        switch (code){
            case Config.SUCCESS_WITH_DATAS:
                onSuccessWithData(t.getData());
                break;
            case Config.SUCCESS_WITH_STATUS:
                onSuccessWithMsg(t);
                break;
            case Config.FAILED_WITH_STATUS:
                _onError(new Throwable("error: "+t.getMsg()));
                break;
            case Config.TOKEN_INVALED:
                _onError(new Throwable("error: "+t.getMsg()));
                break;
        }
    }

    public abstract void onSuccessWithData(T t);

    public abstract void onSuccessWithMsg(HttpResult<T> httpResult);

    public abstract void _onError(Throwable throwable);
}
