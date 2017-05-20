package com.price.take_new.view.activity.home.express;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.takeretrofit.bean.otheruserinfo.OtherUserInfoData;
import com.price.take_new.Constant;
import com.price.take_new.HomeBaseActivity;
import com.price.take_new.R;
import com.price.take_new.presenter.GetOtherUserInfoPresenter;
import com.price.take_new.service.viewService.IGetOtherUserInfoView;
import com.price.take_new.view.activity.notification.SMSCotentActivity;

import org.w3c.dom.Text;

import io.rong.imkit.RongIM;


/**
 * 代拿快递快递详情
 * Created by Administrator on 2016/9/3.
 */
public class ExpressHelpDetailActivity extends HomeBaseActivity implements View.OnClickListener, IGetOtherUserInfoView {

    TextView requestName;
    TextView requestMajor;
    TextView company;
    TextView place;
    TextView time;
    TextView address;
    TextView status;
    TextView tv_sms;

    private String poster_id;
    private String posterName;
    private String sms_content;

    private Button btn_chat;

    private GetOtherUserInfoPresenter presenter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_express_help_detail;
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
        initView();
        if(intent!=null){
            poster_id = intent.getStringExtra(Constant.KEY_POSTER_ID);
            presenter.getUserInfo(intent.getStringExtra(Constant.KEY_TOKEN),poster_id);
            posterName = intent.getStringExtra(Constant.KEY_NAME);
            requestName.setText(intent.getStringExtra(Constant.KEY_NAME));
            company.setText(intent.getStringExtra(Constant.KEY_COMPANY));
            place.setText(intent.getStringExtra(Constant.KEY_PLACE));
            time.setText(intent.getStringExtra(Constant.KEY_TAKE_TIME));
            address.setText(intent.getStringExtra(Constant.KEY_ADDRESS));
            String statu = intent.getStringExtra(Constant.KEY_STATUS);
            sms_content = intent.getStringExtra(Constant.KEY_SMS);
            switch (statu){
                case "0":
                    status.setText("");
                    break;
                case "1":
                    status.setText("已完成");
                    break;
                case "2":
                    status.setText("进行中");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void initView() {
        presenter = new GetOtherUserInfoPresenter(this);
        requestName = (TextView) findViewById(R.id.express_help_detail_request);
        requestMajor = (TextView) findViewById(R.id.express_help_detail_request_major);
        company = (TextView) findViewById(R.id.express_help_detail_company);
        place = (TextView) findViewById(R.id.express_help_detail_place);
        time = (TextView) findViewById(R.id.express_help_detail_time);
        address = (TextView) findViewById(R.id.express_help_detail_finish_location);
        status = (TextView) findViewById(R.id.express_help_detail_status);
        btn_chat = (Button) findViewById(R.id.btn_send_to_request);
        btn_chat.setOnClickListener(this);
        tv_sms = (TextView) findViewById(R.id.sys_notification_sms);
        tv_sms.setOnClickListener(this);
    }

    @Override
    public String getToolBarTitle() {
        return "快递详情";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sys_notification_sms:
                Intent intent = new Intent(this,SMSCotentActivity.class);
                intent.putExtra(Constant.KEY_SMS,sms_content);
                startActivity(intent);
                break;
            case R.id.btn_send_to_request:
                if (RongIM.getInstance() != null) {
                    RongIM.getInstance().startPrivateChat(this, poster_id, posterName);
                }
                this.finish();
                break;
        }

    }

    @Override
    public void onBind(OtherUserInfoData info) {
        String major = info.getMajor();
        if(major!=null)
            requestMajor.setText(major);
    }

    @Override
    public void showToast(String msg) {

    }
}
