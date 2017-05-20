package com.price.take_new.presenter;

import com.example.takeretrofit.bean.sysnotification.NotificationDatum;
import com.price.take_new.model.SysNotificationModel;
import com.price.take_new.service.api.OnSysNotificationListener;
import com.price.take_new.service.modelService.ISysNotificationModel;
import com.price.take_new.service.viewService.ISysNotificationView;

import java.util.List;

/**
 * Created by price on 3/15/2017.
 */

public class SysNotificationPresenter {
    private ISysNotificationModel model;
    private ISysNotificationView view;

    public SysNotificationPresenter(ISysNotificationView view) {
        this.model = new SysNotificationModel();
        this.view = view;
    }

    public void sysNotification(String token,String page,String size){
        model.getExpress(token, page, size, new OnSysNotificationListener() {
            @Override
            public void onSuccess(List<NotificationDatum> mData) {
                view.showData(mData);
            }

            @Override
            public void onMsg(String msg) {
                view.showToast(msg,0);
            }

            @Override
            public void onError(String msg) {
                view.showToast(msg,0);
            }
        });
    }
}
