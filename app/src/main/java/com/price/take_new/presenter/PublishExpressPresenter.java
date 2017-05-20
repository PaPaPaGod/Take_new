package com.price.take_new.presenter;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.price.take_new.Constant;
import com.price.take_new.R;
import com.price.take_new.model.PublisExpressModel;
import com.price.take_new.service.api.OnPublishListener;
import com.price.take_new.service.modelService.IPublishExpressModel;
import com.price.take_new.service.viewService.IPublishExpressView;

/**
 * Created by price on 2/21/2017.
 */

public class PublishExpressPresenter {
    private IPublishExpressModel publishExpressModel;
    private IPublishExpressView publishExpressView;

    public PublishExpressPresenter(IPublishExpressView publishExpressView) {
        this.publishExpressView = publishExpressView;
        this.publishExpressModel = new PublisExpressModel();
    }

    public void publish(String token, String company, String des, String address, String place,
                        String price, String take_time,String sms_content){
        publishExpressModel.publish(token, company, des, address, place, price, take_time,sms_content,new OnPublishListener() {
            @Override
            public void onSuccess(String msg,int code) {
                Log.e("publish",msg);
                publishExpressView.showToast(msg,code);
            }

            @Override
            public void onError(Throwable throwable,int code) {
                publishExpressView.showToast(throwable.getMessage(),code);
                Log.e("publish",throwable.getMessage());
            }
        });
    }

    public boolean checkInput(String[]data){
        int i = 0;
        for(String msg:data){
            if(TextUtils.isEmpty(msg)){
                if(i == 7){
                    return true;
                }else {
                    publishExpressView.showToast("请输入完整信息", Constant.FAILED_WITH_MSG);
                    return false;
                }
            }
            i++;
        }
        return true;
    }
}
