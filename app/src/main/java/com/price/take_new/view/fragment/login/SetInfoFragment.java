package com.price.take_new.view.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.price.take_new.BaseFragment;
import com.price.take_new.Constant;
import com.price.take_new.R;
import com.price.take_new.presenter.SetUserInfoPresenter;
import com.price.take_new.service.viewService.ISetUserInfoView;
import com.price.take_new.view.activity.home.HomeActivity;

/**
 * Created by price on 2/23/2017.
 */

public class SetInfoFragment extends BaseFragment implements AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener, View.OnClickListener, ISetUserInfoView {

    private EditText et_name;
    private EditText et_major;

    private AppCompatSpinner school;
    private RadioGroup sex;
    private RadioButton male;
    private RadioButton female;

    private Button btn_submit;

    private String selected_school;
    private String selected_sex;
    private String name;
    private String major;
    private String token;
    private String ry_token;

    Toolbar mToolbar;
    TextView toolBarTitle;

    private SetUserInfoPresenter presenter;

    public static SetInfoFragment instance = new SetInfoFragment();

    public static SetInfoFragment newInstance() {
        if(instance == null){
            instance = new SetInfoFragment();
        }
        return instance;
    }


    private void setToken(Bundle bundle){
        if(bundle!=null){
            token = bundle.getString(Constant.KEY_TOKEN);
            Log.e("set_token",token);
        }
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        et_name = (EditText) view.findViewById(R.id.setPersonalName);
        et_major = (EditText) view.findViewById(R.id.setMajor);
//        et_dor = (EditText) view.findViewById(R.id.setDormitory);
//        et_phone_num = (EditText) findViewById(R.id.setPhoneNum);

        school = (AppCompatSpinner) view.findViewById(R.id.spinner_school);
        sex = (RadioGroup) view.findViewById(R.id.radio_sex);
        school.setOnItemSelectedListener(this);
        sex.setOnCheckedChangeListener(this);
        male = (RadioButton) view.findViewById(R.id.radio_male);
        female = (RadioButton) view.findViewById(R.id.radio_female);

        presenter = new SetUserInfoPresenter(this);

        btn_submit = (Button) view.findViewById(R.id.btn_submit_info);
        btn_submit.setOnClickListener(this);
        setToolbar(view);

        setToken(getArguments());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setpersonalinfo;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selected_school = "1";
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId == male.getId()){
            Log.e("setPersonSex","男");
            selected_sex = "男";
        }else{
            if(checkedId == female.getId()){
                Log.e("setPersonSex","女");
                selected_sex = "女";
            }
        }
    }
    @Override
    public void onClick(View v) {
        name = et_name.getText().toString();
        major = et_major.getText().toString();
        presenter.set(token,name,selected_sex,selected_school,major,getActivity());
    }

    public void setToolbar(View view){
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        getHoldingActivity().setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFragment();
                getActivity().finish();
            }
        });

        toolBarTitle = (TextView) view.findViewById(R.id.toolbar_title);
        toolBarTitle.setText("设置个人资料");
        getHoldingActivity().getSupportActionBar().setDisplayShowTitleEnabled(false);
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
            }else {
                intent.putExtra(Constant.KEY_RONG_TOKEN,ry_token);
            }
            startActivity(intent);
        }
    }
}
