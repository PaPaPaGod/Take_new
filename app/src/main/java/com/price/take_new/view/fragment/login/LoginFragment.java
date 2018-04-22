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

import com.example.takeretrofit.utils.ManagerData;
import com.price.take_new.BaseFragment;
import com.price.take_new.Constant;
import com.price.take_new.R;
import com.price.take_new.presenter.GetUserInfoPresenter;
import com.price.take_new.presenter.LoginPresenter;
import com.price.take_new.rong.Rong;
import com.price.take_new.service.viewService.ILoginView;
import com.price.take_new.view.activity.HomeActivity;
import com.price.take_new.view.activity.MainTabActivity;

import io.rong.imkit.RongIM;


/**
 * Created by price on 2/15/2017.
 */

public class LoginFragment extends BaseFragment implements ILoginView {
    private static final String TAG = "LoginFragment";
    private Button btn_login;
    private EditText et_phone,et_password;
    private TextView tv_register,tv_forget_password;
    private static LoginFragment instance = new LoginFragment();
    private ProgressDialog loginProgressDialog;

    private GetUserInfoPresenter userInfoPresenter;
    private LoginPresenter loginPresenter;


    public static LoginFragment newInstance() {
        if(instance == null){
            instance = new LoginFragment();
        }
        return instance;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        loginPresenter = new LoginPresenter(this);
        et_phone = (EditText) view.findViewById(R.id.et_login);
        et_password = (EditText) view.findViewById(R.id.et_password);
        btn_login = (Button) view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

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

    private void login() {
        String phone_num;
        String password;
        phone_num = et_phone.getText().toString();
        password = et_password.getText().toString();
        if(phone_num.isEmpty()){
            Toast.makeText(getActivity(),"请输入电话号码", Toast.LENGTH_SHORT).show();
            return;
        }else if (password.isEmpty()){
            Toast.makeText(getActivity(),"请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        loginProgressDialog.show();
        loginPresenter.login(phone_num, password,getActivity());
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToHome(String token, String ry_token) {
        Intent intent = new Intent(getActivity(),HomeActivity.class);
//        intent.putExtra(Constant.FRAGMENT_NAME,Constant.SETTING);
        Bundle data = new Bundle();
        data.putString(Constant.KEY_TOKEN,token);
        Log.e(TAG, "LoginFragment getAuth:"+ManagerData.getAuth(getActivity()));
        if(TextUtils.isEmpty(ry_token)){
            goToSetInfo(token);
        }else {
            data.putString(Constant.KEY_RONG_TOKEN, ry_token);
            Log.e(TAG, "LOGINFRAGMENT::token:: "+token+"/n"+"  ry_token:: "+ry_token);
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
        Bundle bundle = new Bundle();
        bundle.putString(Constant.KEY_TOKEN,token);
        SetUserMsgFragment.newInstance().setArguments(bundle);
        addFragment(SetUserMsgFragment.newInstance());
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
