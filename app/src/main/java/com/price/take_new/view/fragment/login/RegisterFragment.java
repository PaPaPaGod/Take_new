package com.price.take_new.view.fragment.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.takeretrofit.utils.MD5Tool;
import com.price.take_new.BaseFragment;
import com.price.take_new.Constant;
import com.price.take_new.R;
import com.price.take_new.presenter.RegisterPresenter;
import com.price.take_new.service.viewService.IRegisterView;

/**
 * Created by price on 2/18/2017.
 */

public class RegisterFragment extends BaseFragment implements IRegisterView, View.OnClickListener {
    private EditText et_phone;
    private EditText et_password;
    private EditText et_confirm_password;
    private Button btn_commit;

    private RegisterPresenter presenter;

    private static RegisterFragment instance = new RegisterFragment();

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        et_phone = (EditText) view.findViewById(R.id.et_register_phone);
        et_password = (EditText) view.findViewById(R.id.et_register_password);
        et_confirm_password = (EditText) view.findViewById(R.id.et_register_confirm_password);
        btn_commit = (Button) view.findViewById(R.id.btn_finish_register);
        btn_commit.setOnClickListener(this);
        presenter = new RegisterPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    public static RegisterFragment newInstance() {
        if(instance == null){
            instance = new RegisterFragment();
        }
        return instance;
    }

    @Override
    public void showToast(String msg,int code) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
        if(code == Constant.SUCCESS_BACK){
            removeFragment();
        }
    }

    @Override
    public void onClick(View v) {
        String phone = et_phone.getText().toString();
        String password = et_password.getText().toString();
        String confirm_password = et_confirm_password.getText().toString();
        Log.e("text",phone+"  "+password+"  "+confirm_password);
        boolean isTrue = presenter.check(phone,password,confirm_password);
        if(!isTrue){
            return;
        }
        presenter.register(phone, password);
    }
}
