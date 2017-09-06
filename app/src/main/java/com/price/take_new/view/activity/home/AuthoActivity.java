package com.price.take_new.view.activity.home;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.price.take_new.Constant;
import com.price.take_new.HomeBaseActivity;
import com.price.take_new.R;
import com.price.take_new.presenter.AuthPresenter;
import com.price.take_new.service.viewService.IAuthView;

/**
 * Created by price on 2/22/2017.
 */
public class AuthoActivity extends HomeBaseActivity implements View.OnClickListener, IAuthView {
    EditText stu_id;
    EditText stu_password;
    Button submit;
    private String token;

    private AuthPresenter authPresenter;


    @Override
    public void initView() {
        stu_id = (EditText) findViewById(R.id.et_autho_stu_id);
        stu_password = (EditText) findViewById(R.id.et_autho_stu_password);
        submit = (Button) findViewById(R.id.btn_submit_autho);
        submit.setOnClickListener(this);
        authPresenter = new AuthPresenter(this);
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
        if(intent!=null){
            token = intent.getStringExtra(Constant.KEY_TOKEN);
        }
    }

    @Override
    public String getToolBarTitle() {
        return "我要验证";
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_autho;
    }


    @Override
    public void onClick(View v) {
        String id = "";
        id = stu_id.getText().toString();
        String pas ="";
        pas = stu_password.getText().toString();
        if(TextUtils.isEmpty(id) || TextUtils.isEmpty(pas)){
            Toast.makeText(this,"请填写完整数据",Toast.LENGTH_SHORT).show();
            return;
        }
        authPresenter.auth(token,id,pas);
    }

    @Override
    public void showToast(String msg, int code) {
        if(code == Constant.SUCCESS_WITH_MSG){
            Toast.makeText(this,"已提交审核，请耐心等待",Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}
