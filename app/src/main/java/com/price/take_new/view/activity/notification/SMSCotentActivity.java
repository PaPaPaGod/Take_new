package com.price.take_new.view.activity.notification;

import android.content.Intent;
import android.widget.TextView;

import com.price.take_new.AppActivity;
import com.price.take_new.Constant;
import com.price.take_new.HomeBaseActivity;
import com.price.take_new.R;

/**
 * Created by price on 4/2/2017.
 */
public class SMSCotentActivity extends HomeBaseActivity{
    private String sms_content;

    private TextView tv_sms_content;

    @Override
    public void initView() {
        tv_sms_content = (TextView) findViewById(R.id.tv_sms_content);
        if(sms_content!=null){

            if(sms_content.equals("")){
                tv_sms_content.setText("发布者很懒，什么都没留下");
            }else {
                tv_sms_content.setText(sms_content);
            }

        }else{
            tv_sms_content.setText("发布者很懒，什么都没留下");
        }
    }

    @Override
    public String getToolBarTitle() {
        return "短信内容";
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
        sms_content = intent.getStringExtra(Constant.KEY_SMS);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_sms_content;
    }
}
