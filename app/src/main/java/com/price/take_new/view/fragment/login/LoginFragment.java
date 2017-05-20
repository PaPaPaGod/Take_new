package com.price.take_new.view.fragment.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.takeretrofit.bean.userinfo.UserInfoData;
import com.example.takeretrofit.utils.MD5Tool;
import com.example.takeretrofit.utils.ManagerData;
import com.price.take_new.BaseFragment;
import com.price.take_new.Constant;
import com.price.take_new.R;
import com.price.take_new.bean.UserInfo;
import com.price.take_new.presenter.GetUserInfoPresenter;
import com.price.take_new.presenter.LoginPresenter;
import com.price.take_new.service.viewService.IGetUserInfoView;
import com.price.take_new.service.viewService.ILoginView;
import com.price.take_new.view.activity.SetInfoActivity;
import com.price.take_new.view.activity.home.HomeActivity;


/**
 * Created by price on 2/15/2017.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener, ILoginView {
    private Button btn_login;
    private EditText et_phone,et_password;
    private TextView tv_register,tv_forget_password;
    private static LoginFragment instance = new LoginFragment();
    ProgressDialog loginProgressDialog;

    private static final String LOGINFRAGMENT = "loginFragment";

    private LoginPresenter loginPresenter;
//    private GetUserInfoPresenter infoPresenter;

    public static LoginFragment newInstance() {
        if(instance == null){
            instance = new LoginFragment();
        }
        return instance;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
//        infoPresenter = new GetUserInfoPresenter(this);
        loginPresenter = new LoginPresenter(this);
        btn_login = (Button) view.findViewById(R.id.login);
        et_phone = (EditText) view.findViewById(R.id.et_userNameId);
        et_password = (EditText) view.findViewById(R.id.et_login_password);
        tv_register = (TextView) view.findViewById(R.id.tv_login_register);
        tv_forget_password = (TextView) view.findViewById(R.id.tv_forget_password);
        btn_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        loginProgressDialog = ProgressDialog.show(getActivity(),
                getResources().getString(R.string.LoginProgressDialogTitle),
                getResources().getString(R.string.LoginProgressDialogContent));
        loginProgressDialog.dismiss();
        loginProgressDialog.setCancelable(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                login();
                break;

            case R.id.tv_login_register:
                addFragment(RegisterFragment.newInstance());
                break;

            case R.id.tv_forget_password:
                addFragment(ResetPasswordFragment.newInstance());
                break;
        }
    }

    private void login() {
//        Bundle data = new Bundle();
        String phone_num;
        String password;
        phone_num = et_phone.getText().toString();
        password = et_password.getText().toString();
        if(phone_num.isEmpty()){
            Log.e(LOGINFRAGMENT,"no phone");
            Toast.makeText(getActivity(),"请输入电话号码",Toast.LENGTH_SHORT).show();
            return;
        }else if (password.isEmpty()){
            Toast.makeText(getActivity(),"请输入密码",Toast.LENGTH_SHORT).show();
            return;
        }
        loginProgressDialog.show();
        loginPresenter.login(phone_num, password,getActivity());
    }

    @Override
    public String getPhoneNum() {
        return et_phone.getText().toString();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void setPhoneNum(String phoneNum) {

    }

    @Override
    public void setPassword(String password) {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToHome(String token, String ry_token) {
        Intent intent = new Intent(getActivity(),HomeActivity.class);
        Bundle data = new Bundle();
        data.putString("token",token);
        Log.e("isAuth", ManagerData.getAuth(getActivity())+"");
        if(TextUtils.isEmpty(ry_token)){
            goToSetInfo(token);
        }else {
            data.putString("ry_token", ry_token);
            Log.e(LOGINFRAGMENT, "token:: "+token+"/n"+"  ry_token:: "+ry_token);
            intent.putExtras(data);
            startActivity(intent);
            removeFragment();
            getActivity().finish();
        }
    }

    @Override
    public void showLoading() {
        loginProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        loginProgressDialog.dismiss();
    }

    @Override
    public void goToSetInfo(String token) {
        Intent intent = new Intent(getActivity(), SetInfoActivity.class);
        intent.putExtra(Constant.KEY_TOKEN,token);
        startActivityForResult(intent,0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case Constant.SUCCESS_BACK:
                removeFragment();
                getActivity().finish();
                break;
            case Constant.CANCLE_BACK:
                break;
        }
    }
}
