package com.price.take_new.presenter;

import com.price.take_new.App;
import com.price.take_new.Constant;
import com.price.take_new.bean.UserInfo;
import com.price.take_new.bean.UserInfoDao;
import com.price.take_new.model.DeleteFriendModel;
import com.price.take_new.service.api.AcceptExpressListener;
import com.price.take_new.service.api.DeleteFriendListener;
import com.price.take_new.service.modelService.IDeleteFriendModel;
import com.price.take_new.service.viewService.IDeleteFriendView;

/**
 * Created by intel on 3/29/2018.
 */

public class DeleteFriendPresenter {
    private IDeleteFriendView deleteFriendView;
    private IDeleteFriendModel deleteFriendModel;
    private UserInfoDao dao;

    public DeleteFriendPresenter(IDeleteFriendView deleteFriendView) {
        this.deleteFriendView = deleteFriendView;
        this.deleteFriendModel = new DeleteFriendModel();
        dao = App.getDaoSession().getUserInfoDao();
    }

    public void deleteFriend(String token, String user_id){
        deleteFriendModel.deleteFriend(token, user_id, new DeleteFriendListener() {
            @Override
            public void onSuccess(String msg, int code) {
                if(code == Constant.SUCCESS_WITH_MSG){
                    deleteFriendView.showMsg(msg,code);
                }
            }

            @Override
            public void onError(String msg, int code) {
                deleteFriendView.showMsg(msg,code);
            }
        });
    }
}
