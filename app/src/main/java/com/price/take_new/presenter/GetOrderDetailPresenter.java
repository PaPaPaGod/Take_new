package com.price.take_new.presenter;

import android.util.Log;

import com.example.takeretrofit.bean.orderdetail.OrderDetailData;
import com.price.take_new.model.GetOrderDetailModel;
import com.price.take_new.service.api.OnGetOrderDetailListener;
import com.price.take_new.service.modelService.IGetOrderDetailModel;
import com.price.take_new.service.viewService.IGetOrderDetailView;

/**
 * Created by price on 3/16/2017.
 */

public class GetOrderDetailPresenter {
    private IGetOrderDetailModel model;
    private IGetOrderDetailView view;

    public GetOrderDetailPresenter(IGetOrderDetailView view) {
        this.view = view;
        this.model = new GetOrderDetailModel();
    }

    public void getOrderDetail(String token,String order_id){
        model.getOrderDetail(token, order_id, new OnGetOrderDetailListener() {
            @Override
            public void onSuccess(OrderDetailData data) {
                view.onBindOrderDetail(data);
                if(data.getSms_content()!=null){
                    Log.e("sms_content",data.getSms_content());
                }else{
                    Log.e("sms_content","null");
                }
            }

            @Override
            public void onMsg(String msg) {
                view.showToast(msg);
            }

            @Override
            public void onError(String msg) {
                view.showToast(msg);
            }
        });
    }
}
