package com.price.take_new.presenter;

import android.content.Context;
import android.util.Log;

import com.example.takeretrofit.utils.ManagerData;
import com.price.take_new.App;
import com.price.take_new.Constant;
import com.price.take_new.bean.UserInfo;
import com.price.take_new.model.SetUserInfoModel;
import com.price.take_new.service.api.OnSetUserInfoListener;
import com.price.take_new.service.modelService.ISetInfoModel;
import com.price.take_new.service.viewService.ISetUserInfoView;
import com.price.take_new.utils.ManagerUserInfo;

/**
 * Created by price on 2/23/2017.
 */

public class SetUserInfoPresenter {
    private ISetInfoModel model;
    private ISetUserInfoView view;
    private GetUserInfoPresenter presenter;

    public SetUserInfoPresenter(ISetUserInfoView view) {
        this.view = view;
        model = new SetUserInfoModel();
//        presenter = new GetUserInfoPresenter(null);
    }

    public void set(final String token, final String name, final String sex, String school_id, final String major, final Context context){
        Log.e("set info:  ",name+"  "+sex+"  "+major);
        model.set(token, name, sex, school_id, major, new OnSetUserInfoListener() {
            @Override
            public void onSuccess(String msg,String data, int code) {
                String result = "";
                UserInfo info = new UserInfo();
                info.setToken(token);
                info.setName(name);
                info.setSex(sex);
                info.setMajor(major);
                info.setPhoneNum(ManagerData.getPhoneNum(context));
                info.setSchool("仲恺农业大学");
                info.setAuth("0");
                ManagerUserInfo.cacheInfo(context,info);
                Log.e("set info ry_token:",data);
                if(code == Constant.SUCCESS_WITH_DATA){
                    result = "设置完成";
                    ManagerData.cacheRongToken(App.getContext(),data);
                    view.bindRy_token(data);
                }else{
                    result = msg;
                }
                view.showToast(result,code);
//                presenter.cacheInfo();
            }

            @Override
            public void onError(String msg, int code) {
                view.showToast(msg,code);
            }
        });
    }
}
