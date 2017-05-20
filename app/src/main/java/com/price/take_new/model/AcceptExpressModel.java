package com.price.take_new.model;

import android.util.Log;

import com.example.takeretrofit.api.GetOrderApi;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultMsgSubscriber;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithoutdata.HttpResultWithoutData;
import com.example.takeretrofit.utils.TransFormUtils;
import com.price.take_new.Constant;
import com.price.take_new.bean.UserInfo;
import com.price.take_new.service.api.AcceptExpressListener;
import com.price.take_new.service.modelService.IAcceptExpressModel;

/**
 * Created by price on 2/22/2017.
 */

public class AcceptExpressModel implements IAcceptExpressModel {

    private String tag = "getOrder_test";

    @Override
    public void getExpress(String token, String poster_id, String express_id, UserInfo info,final AcceptExpressListener acceptExpressListener) {
        ServiceFactory.getInstance().createService(GetOrderApi.class)
                .getOrder(token,poster_id,express_id)
                .compose(TransFormUtils.<HttpResultWithoutData>defaultSchedulers())
                .subscribe(new HttpResultMsgSubscriber() {
                    @Override
                    public void onSuccessWithMsg(String result) {
                        Log.e(tag,result);
                        acceptExpressListener.onSuccess(result, Constant.SUCCESS_WITH_MSG);
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(tag,throwable.getMessage());
                        acceptExpressListener.onError(throwable.getMessage(),Constant.FAILED_WITH_EXCEPTION);
                    }
                });
    }
}
