package com.price.take_new.presenter;

import com.price.take_new.model.ManagerExpressModel;
import com.price.take_new.service.api.OnManagerExpressListener;
import com.price.take_new.service.modelService.IManagerExpressModel;
import com.price.take_new.service.viewService.IManagerExpressView;

/**
 * Created by price on 2/24/2017.
 */

public class ManagerExpressPresenter {
    private IManagerExpressModel updateExpressModel;
    private IManagerExpressView updateExpressView;

    public ManagerExpressPresenter(IManagerExpressView updateExpressView) {
        this.updateExpressView = updateExpressView;
        updateExpressModel = new ManagerExpressModel();
    }

    public void update(String token, String company, String des, String address,String order_id,
                       String place, String nickname,
                       int weight_type,int at_school,String sms_content,String price,String small_reward){
        updateExpressModel.update(token, order_id, company, address, place,nickname,weight_type,des,at_school,
                sms_content,price, small_reward, new OnManagerExpressListener() {
            @Override
            public void onSuccess(String msg, int code) {
                updateExpressView.showToast(msg, code);
            }

            @Override
            public void onError(String msg, int code) {
                updateExpressView.showToast(msg, code);
            }
        });
    }

    public void delete(String token, String order_id){
        updateExpressModel.delete(token, order_id, new OnManagerExpressListener() {
            @Override
            public void onSuccess(String msg, int code) {
                updateExpressView.showToast(msg, code);
            }

            @Override
            public void onError(String msg, int code) {
                updateExpressView.showToast(msg, code);
            }
        });
    }

    public void showDialog(){
        updateExpressView.showDialog();
    }
}
