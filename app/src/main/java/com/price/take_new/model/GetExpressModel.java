package com.price.take_new.model;

import android.text.TextUtils;
import android.util.Log;

import com.example.takeretrofit.api.DeliveryApi;
import com.example.takeretrofit.bean.delivery.DeliveryDatum;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResultSubscriber;
import com.example.takeretrofit.utils.TransFormUtils;
import com.price.take_new.Constant;
import com.price.take_new.service.api.OnExpressListener;
import com.price.take_new.service.modelService.IGetExpressModel;

import java.util.List;

/**获取快递页列表
 * Created by price on 2/20/2017.
 */

public class GetExpressModel implements IGetExpressModel {

    private static final String TAG = "delivery_module";

    @Override
    public void getExpress(String token, int page, String filter,final OnExpressListener onExpressListener) {
        ServiceFactory.getInstance().createService(DeliveryApi.class)
                .getDelivery(token, page,filter)
                .compose(TransFormUtils.<HttpResult<List<DeliveryDatum>>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<List<DeliveryDatum>>() {
                    @Override
                    public void onSuccessWithData(List<DeliveryDatum> deliveryData) {
                        if(deliveryData.equals(null)){
                            onExpressListener.onError(new Throwable("返回数据失败，请稍候重试"),Constant.FAILED_WITH_EXCEPTION);
                            return;
                        }
                        onExpressListener.onSuccess(deliveryData, Constant.SUCCESS_WITH_DATA);

                    }

                    @Override
                    public void onSuccessWithMsg(HttpResult<List<DeliveryDatum>> httpResult) {
                        Log.e(TAG,httpResult.getData().get(0).getName());
                        String msg = "";
                        msg = httpResult.getMsg();
                        if(TextUtils.isEmpty(msg)) {
                            onExpressListener.onError(new Throwable(msg),Constant.FAILED_WITH_EXCEPTION);
                            return;
                        }
                        onExpressListener.onMsg(msg,Constant.SUCCESS_WITH_MSG);
                    }

                    @Override
                    public void _onError(Throwable throwable) {
                        Log.e(TAG,throwable.getMessage());
                        if(throwable.equals(null)){
                            onExpressListener.onError(new Throwable("返回数据失败，请稍候重试"),Constant.FAILED_WITH_EXCEPTION);
                            return;
                        }
                        onExpressListener.onError(throwable,Constant.FAILED_WITH_EXCEPTION);
                    }
                });
    }
}
