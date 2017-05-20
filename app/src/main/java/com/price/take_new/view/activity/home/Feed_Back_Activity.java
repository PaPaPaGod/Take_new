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
import com.price.take_new.presenter.FeedBackPresenter;
import com.price.take_new.service.viewService.IFeedBackView;

/**
 * Created by price on 2/22/2017.
 */
public class Feed_Back_Activity extends HomeBaseActivity implements View.OnClickListener, IFeedBackView {
    private Button submitButton;
    private EditText editText;

    private String token;

    private FeedBackPresenter feedBackPresenter;

    @Override
    public void initView() {
        submitButton = (Button) findViewById(R.id.submit);
        editText = (EditText) findViewById(R.id.feed_back_editText);
        submitButton.setOnClickListener(this);
        feedBackPresenter = new FeedBackPresenter(this);
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
        return "我要吐槽";
    }

    @Override
    public int getContentViewId() {
        return R.layout.feedback;
    }

    @Override
    public void onClick(View v) {
        String des = "";
        des = editText.getText().toString();
        if(!TextUtils.isEmpty(des)) {
            feedBackPresenter.feedBack(token,des);
        }else{
            Toast.makeText(this,"请输入建议内容",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showToast(String msg, int code) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        if(code == Constant.SUCCESS_WITH_MSG){
            finish();
        }
    }
}
