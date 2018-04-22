package com.price.take_new.view.fragment.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.takeretrofit.utils.ManagerData;
import com.price.take_new.BaseFragment;
import com.price.take_new.Constant;
import com.price.take_new.R;
import com.price.take_new.presenter.SetUserInfoPresenter;
import com.price.take_new.service.viewService.ISetUserInfoView;
import com.price.take_new.view.activity.HomeActivity;
import com.price.take_new.view.activity.LoginAcitivity;


/**
 * Created by intel on 2/3/2018.
 */

public class SetUserMsgFragment extends BaseFragment implements ISetUserInfoView {

    private static final String TAG = "SetUserMsgFragment";
    private String token;
    private String ry_token;
    private String name;
    private String tbname;
    private String major;
    private String dorm;
    private String sex;
    private String school_id;

    private RadioGroup mRadioGroup;
    private RadioButton mRBMale;
    private RadioButton mRBFemale;

    private EditText et_name;
    private EditText et_tb_name;
    private EditText et_major;
    private EditText et_dorm;

    private AppCompatSpinner mSpinner;

    private Button btn_register;

    private SetUserInfoPresenter infoPresenter;


    private static SetUserMsgFragment instance = new SetUserMsgFragment();
    public static SetUserMsgFragment newInstance(){
        if(instance == null){
            instance = new SetUserMsgFragment();
        }
        return instance;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        infoPresenter = new SetUserInfoPresenter(this);
        et_name = (EditText) view.findViewById(R.id.et_info_name);
        et_tb_name = (EditText) view.findViewById(R.id.et_info_tbname);
        et_major = (EditText) view.findViewById(R.id.et_info_major);
        et_dorm = (EditText) view.findViewById(R.id.et_info_dorm);
        mSpinner = (AppCompatSpinner) view.findViewById(R.id.info_spinner_school);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                school_id = "1";
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mRBMale = (RadioButton) view.findViewById(R.id.info_rb_male);
        mRBFemale = (RadioButton) view.findViewById(R.id.info_rb_female);
        sex = "男";
        mRadioGroup = (RadioGroup) view.findViewById(R.id.info_rg);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.info_rb_male:
                        sex = "男";
                        break;
                    case R.id.info_rb_female:
                        sex = "女";
                        break;
                }
            }
        });
        btn_register = (Button) view.findViewById(R.id.register_btn);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUserInfo();
//                infoPresenter.set(token,name,);
            }
        });
        Bundle bundle = getArguments();
        token = bundle.getString(Constant.KEY_TOKEN);
    }

    private void setUserInfo() {
        // TODO: 3/6/2018  getInfo from ui
        getData();
        infoPresenter.set(token,name,sex,school_id,major,getActivity());
//        ManagerData.cacheTBName(getActivity(),tbname);
    }

    private void getData() {
        name = et_name.getText().toString();
        major = et_major.getText().toString();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    public void bindRy_token(String ry_token) {
        this.ry_token = ry_token;
    }

    @Override
    public void showToast(String msg, int code) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
        if(code == Constant.SUCCESS_WITH_DATA){
            getActivity().setResult(Constant.SUCCESS_BACK);
            removeFragment();
            getActivity().finish();
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            intent.putExtra(Constant.KEY_TOKEN,token);
            if(!TextUtils.isEmpty(ry_token)) {
                intent.putExtra(Constant.KEY_RONG_TOKEN, ry_token);
            }
            ManagerData.cacheTBName(getActivity(),tbname);
            startActivity(intent);
        }
    }

    private void onClick(){
        ManagerData.clearCachedData(getActivity());
        Intent intent = new Intent(getActivity(), LoginAcitivity.class);
        Intent intent1 = new Intent();
        getActivity().setResult(Activity.RESULT_OK,intent1);
        removeFragment();
        getActivity().finish();
        startActivity(intent);
    }
}
