package com.price.take_new.view.fragment.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.price.take_new.BaseFragment;
import com.price.take_new.R;

/**
 * Created by price on 2/18/2017.
 */

public class ResetPasswordFragment extends BaseFragment {
    private static ResetPasswordFragment instance = new ResetPasswordFragment();
    private EditText phone;
    private EditText identifying;
    private EditText password;
    private EditText confirm_password;
    private Button btn_sendCode;

    public static ResetPasswordFragment newInstance() {
        if(instance == null){
            instance = new ResetPasswordFragment();
        }
        return instance;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        phone = (EditText) view.findViewById(R.id.et_reset_phone_number);
        identifying = (EditText) view.findViewById(R.id.et_reset_identifying_code);
        password = (EditText) view.findViewById(R.id.et_reset_enter_new_password);
        confirm_password = (EditText) view.findViewById(R.id.et_reset_confirm_new_password);
        btn_sendCode = (Button) view.findViewById(R.id.btn_reset_send_identifying_code);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reset_password;
    }
}
