package com.price.take_new.view.activity.home;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.price.take_new.Constant;
import com.price.take_new.HomeBaseActivity;
import com.price.take_new.R;
import com.price.take_new.bean.UserInfo;
import com.price.take_new.presenter.GetUserInfoPresenter;
import com.price.take_new.service.viewService.IGetUserInfoView;

/**
 * Created by price on 2/22/2017.
 */
public class Activity_Personal_Document extends HomeBaseActivity implements IGetUserInfoView {

    TextView name;
    TextView school;
    TextView sex;
    TextView phone_num;
    TextView isCertified;
    TextView major;

    private UserInfo info;
    private String token;

    private GetUserInfoPresenter presenter;

    @Override
    public void initView() {
        presenter = new GetUserInfoPresenter(this);
        name = (TextView) findViewById(R.id.personal_document_realName);
        major = (TextView) findViewById(R.id.person_document_major);
        school = (TextView) findViewById(R.id.personal_document_school);
        sex = (TextView) findViewById(R.id.personal_document_sex);
        phone_num = (TextView) findViewById(R.id.personal_document_phoneNumber);
        isCertified = (TextView) findViewById(R.id.personal_document_isAuthentication);
        if(info==null){
            presenter.getUserInfo(token,this);
        }else{
            showData(info);
        }
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
        if(intent.getExtras()!=null){
            info = (UserInfo) intent.getExtras().getSerializable(Constant.USER_INFO);
        }
        token = intent.getStringExtra(Constant.KEY_TOKEN);
    }

    @Override
    public String getToolBarTitle() {
        return "个人资料";
    }

    @Override
    public int getContentViewId() {
        return R.layout.personal_document;
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bindData(UserInfo info) {
        this.info = info;
        showData(info);
    }

    private void showData(UserInfo info) {
        name.setText(info.getName());
        school.setText(info.getSchool());
        sex.setText(info.getSex());
        phone_num.setText(info.getPhoneNum());
        major.setText(info.getMajor());
        String isCerti = info.getAuth();
        Log.e("stu_id",info.getStuId());
        if(isCerti.equals("0")){
            isCerti = "未认证";
            Log.e("isCerti",isCerti);
        }else if(isCerti.equals("1")){
            isCerti = "已认证";
            Log.e("isCerti",isCerti);
        }else if(!TextUtils.isEmpty(info.getStuId())){
            isCerti = "认证中";
            Log.e("isCerti",isCerti);
        }
        isCertified.setText(isCerti);
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoading() {

    }
}
