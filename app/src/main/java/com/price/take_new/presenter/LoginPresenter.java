package com.price.take_new.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.takeretrofit.api.LoginApi;
import com.example.takeretrofit.bean.login.LoginData;
import com.example.takeretrofit.bean.userinfo.UserInfoData;
import com.example.takeretrofit.retrofit.factory.ServiceFactory;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResult;
import com.example.takeretrofit.retrofit.httpsubscriber.httpwithdata.HttpResultSubscriber;
import com.example.takeretrofit.utils.MD5Tool;
import com.example.takeretrofit.utils.ManagerData;
import com.example.takeretrofit.utils.TransFormUtils;
import com.price.take_new.SolidApplication;
import com.price.take_new.model.GetUserInfoModel;
import com.price.take_new.model.LoginModel;
import com.price.take_new.service.api.OnGetInfoListener;
import com.price.take_new.service.api.OnLoginListener;
import com.price.take_new.service.modelService.IGetUserInfoModel;
import com.price.take_new.service.modelService.ILoginModel;
import com.price.take_new.service.viewService.ILoginView;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by price on 2/18/2017.
 */

public class LoginPresenter {
    private ILoginModel loginModel;
    private ILoginView loginView;
    private IGetUserInfoModel userInfoModel;
//    private GetUserInfoPresenter userInfoPresenter;

    private String login_token ;
    private String login_ry_token ;
    private String login_msg ;



    public LoginPresenter(ILoginView loginView) {
        this.loginModel = new LoginModel();
        this.userInfoModel = new GetUserInfoModel();
        this.loginView = loginView;
    }

    public void login(final String phone_num, final String password, final Context context){
        loginModel.login(phone_num, password, new OnLoginListener() {
            @Override
            public void onSuccess(final String token, final String ry_token, int success) {
                loginView.hideLoading();
                loginView.showToast("登陆成功!");
                login_token = token;
                login_ry_token = ry_token;
                if(TextUtils.isEmpty(login_ry_token)){
                    loginView.goToSetInfo(token);
                }else {
                    loginView.showLoading();
                    userInfoModel.getInfo(token, new OnGetInfoListener() {
                        @Override
                        public void onSuccess(UserInfoData data, int code) {
                            loginView.hideLoading();
                            String auth = data.getAuth();
                            boolean isAuth = false;
                            if(auth.equals("1")){
                                isAuth = true;
                            }
                            ManagerData.cacheAuth(context,isAuth);
                            loginView.goToHome(token,ry_token);
                        }

                        @Override
                        public void onMsg(String msg, int code) {
                            loginView.hideLoading();
                        }

                        @Override
                        public void onError(String msg, int code) {
                            loginView.hideLoading();
                        }
                    });
                    cacheData(phone_num,password,token,ry_token,context);
                }
            }

            @Override
            public void onMsg(String msg, int hint) {
                loginView.hideLoading();
                loginView.showToast(msg);
            }

            @Override
            public void onError(Throwable throwable, int error) {
                loginView.hideLoading();
                loginView.showToast(throwable.getMessage());
            }

        });
    }

    public void cacheData(String phone,String password,String token,String ry_token,Context context){
        ManagerData.cachePhoneNum(context,phone);
        ManagerData.cachePassword(context,password);
        ManagerData.cacheToken(context,token);
        ManagerData.cacheRongToken(context,ry_token);
    }

}
