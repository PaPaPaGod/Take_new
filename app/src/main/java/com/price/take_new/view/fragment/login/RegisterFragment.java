package com.price.take_new.view.fragment.login;

import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.price.take_new.BaseFragment;
import com.price.take_new.R;


/**
 * Created by intel on 2/3/2018.
 */

public class RegisterFragment extends BaseFragment {

    private RadioGroup mRadioGroup;
    private RadioButton mRBMale;
    private RadioButton mRBFemale;

    private EditText et_name;
    private EditText et_tb_name;
    private EditText et_major;
    private EditText et_dorm;

    private AppCompatSpinner mSpinner;

    private static RegisterFragment instance = new RegisterFragment();

    public static RegisterFragment newInstance(){
        if(instance == null){
            instance = new RegisterFragment();
        }
        return instance;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        et_name = (EditText) view.findViewById(R.id.et_info_name);
        et_tb_name = (EditText) view.findViewById(R.id.et_info_tbname);
        et_major = (EditText) view.findViewById(R.id.et_info_major);
        et_dorm = (EditText) view.findViewById(R.id.et_info_dorm);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

}
