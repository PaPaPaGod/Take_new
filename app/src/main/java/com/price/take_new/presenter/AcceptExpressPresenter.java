package com.price.take_new.presenter;

import com.price.take_new.App;
import com.price.take_new.Constant;
import com.price.take_new.bean.UserInfo;
import com.price.take_new.bean.UserInfoDao;
import com.price.take_new.model.AcceptExpressModel;
import com.price.take_new.service.api.AcceptExpressListener;
import com.price.take_new.service.modelService.IAcceptExpressModel;
import com.price.take_new.service.viewService.AcceptExpressView;

/**
 * 接受快递
 * Created by price on 2/21/2017.
 */

public class AcceptExpressPresenter {
    private AcceptExpressView acceptExpressView;
    private IAcceptExpressModel acceptExpressModel;
    private UserInfoDao dao;

    public AcceptExpressPresenter(AcceptExpressView acceptExpressView) {
        this.acceptExpressView = acceptExpressView;
        this.acceptExpressModel = new AcceptExpressModel();
        dao = App.getDaoSession().getUserInfoDao();
    }

    public void acceptOrder(String token, String express_id){
        acceptExpressModel.getExpress(token, express_id, new AcceptExpressListener() {
            @Override
            public void onSuccess(String msg, int code) {
                if("不能抢自己发布的订单".equals(msg)) {
                    acceptExpressView.showToast(msg, code);
                    return;
                }
                acceptExpressView.showToast(msg, code);
//                dao.insertOrReplace(info);
                showDialog(Constant.CODE_TALK);
            }

            @Override
            public void onError(String msg, int code) {
                acceptExpressView.showToast(msg,code);
            }
        });
    }

    public void showDialog(int code){
        acceptExpressView.showMyDialog(code);
    }

}
