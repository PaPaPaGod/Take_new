package com.price.take_new.presenter;

import com.example.takeretrofit.bean.mydeliveryorder.MyDeliveryOrderDatum;
import com.example.takeretrofit.bean.myhelpdeliveryorder.MyHelpOrderDatum;
import com.price.take_new.model.MyExpressModel;
import com.price.take_new.service.api.OnMyHelpExpressListener;
import com.price.take_new.service.api.OnMyPublishExpressListener;
import com.price.take_new.service.modelService.IMyExpressModel;
import com.price.take_new.service.viewService.IMyExpressView;

import java.util.List;

/**
 * Created by price on 2/23/2017.
 */

public class MyExpressPresenter {
    private IMyExpressModel model;
    private IMyExpressView view;

    public MyExpressPresenter(IMyExpressView view) {
        this.view = view;
        model = new MyExpressModel();
    }

    public void getMyDelivery(String token, String page, final int index){
        model.getExpress(token, page, new OnMyPublishExpressListener() {
            @Override
            public void onMySuccess(List<MyDeliveryOrderDatum> mData, int code) {
                view.hideLoading(index);
                view.showMyData(mData);
            }

            @Override
            public void onMsg(String msg, int code) {
                view.hideLoading(index);
                view.showToast(msg,code);
            }

            @Override
            public void onError(String msg, int code) {
                view.hideLoading(index);
                view.showToast(msg,code);
            }
        });
    }

    public void getHelpDelivery(String token, String page, final int index){
        model.getHelpExpress(token, page, new OnMyHelpExpressListener() {
            @Override
            public void onHelpSuccess(List<MyHelpOrderDatum> mData, int code) {
                view.hideLoading(index);
                view.showHelpData(mData);
            }

            @Override
            public void onMsg(String msg, int code) {
                view.hideLoading(index);
                view.showToast(msg,code);
            }

            @Override
            public void onError(String msg, int code) {
                view.hideLoading(index);
                view.showToast(msg,code);
            }
        });
    }
}
