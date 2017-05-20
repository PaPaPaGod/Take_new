package com.price.take_new.presenter;

import com.example.takeretrofit.bean.otheruserinfo.OtherUserInfoData;
import com.price.take_new.model.GetOtherUserInfoModel;
import com.price.take_new.service.api.OnGetOtherUserInfoListener;
import com.price.take_new.service.modelService.IGetOtherUserInfo;
import com.price.take_new.service.viewService.IGetOtherUserInfoView;

/**
 * Created by price on 2/25/2017.
 */

public class GetOtherUserInfoPresenter {
    private IGetOtherUserInfo model;
    private IGetOtherUserInfoView view;

    public GetOtherUserInfoPresenter(IGetOtherUserInfoView view) {
        this.view = view;
        model = new GetOtherUserInfoModel();
    }

    public void getUserInfo(String token,String poster_id){
        model.getOtherInfo(token, poster_id, new OnGetOtherUserInfoListener() {
            @Override
            public void onSuccess(OtherUserInfoData info) {
                view.onBind(info);
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
