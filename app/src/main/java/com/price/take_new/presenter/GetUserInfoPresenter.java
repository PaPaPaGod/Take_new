package com.price.take_new.presenter;

import android.content.Context;
import android.util.Log;

import com.example.takeretrofit.bean.userinfo.UserInfoData;
import com.example.takeretrofit.utils.ManagerData;
import com.price.take_new.bean.UserInfo;
import com.price.take_new.model.GetUserInfoModel;
import com.price.take_new.service.api.OnGetInfoListener;
import com.price.take_new.service.modelService.IGetUserInfoModel;
import com.price.take_new.service.viewService.IGetUserInfoView;
import com.price.take_new.service.viewService.ILoginView;

import java.util.List;

/**
 * Created by price on 2/22/2017.
 */

public class GetUserInfoPresenter {
    private IGetUserInfoModel getUserInfoModel;
    private IGetUserInfoView infoView;

    public GetUserInfoPresenter(IGetUserInfoView infoView) {
        this.getUserInfoModel = new GetUserInfoModel();
        this.infoView = infoView;
    }

    private UserInfo info;

    // 联网获取
    public UserInfo getUserInfo(final String token, final Context context){
        getUserInfoModel.getInfo(token, new OnGetInfoListener() {
            @Override
            public void onSuccess(UserInfoData data, int code) {
                info = new UserInfo();
                info.setPhoneNum(data.getPhoneNum());
                info.setSchool(data.getSchool());
                info.setStuId(data.getStuId());
                info.setToken(token);
                info.setSex(data.getSex());
                info.setName(data.getName());
                info.setAuth(data.getAuth());
                boolean isAuth = false;
                if(data.getAuth().equals("1")){
                    isAuth = true;
                }
                ManagerData.cacheAuth(context,isAuth);
                info.setMajor(data.getMajor());
                infoView.bindData(info);
            }

            @Override
            public void onMsg(String msg, int code) {
                infoView.showToast(msg);
            }

            @Override
            public void onError(String msg, int code) {
                infoView.showToast(msg);
            }
        });
        return info;
    }
}
