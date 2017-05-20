package com.price.take_new.view.activity.notification;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.takeretrofit.bean.orderdetail.OrderDetailData;
import com.example.takeretrofit.bean.otheruserinfo.OtherUserInfoData;
import com.example.takeretrofit.model.GetOtherUserInfo;
import com.price.take_new.Constant;
import com.price.take_new.HomeBaseActivity;
import com.price.take_new.R;
import com.price.take_new.presenter.GetExpressPresenter;
import com.price.take_new.presenter.GetOrderDetailPresenter;
import com.price.take_new.presenter.GetOtherUserInfoPresenter;
import com.price.take_new.rong.Rong;
import com.price.take_new.service.viewService.IGetOrderDetailView;
import com.price.take_new.service.viewService.IGetOtherUserInfoView;

import io.rong.imkit.RongIM;

/**
 * Created by price on 3/15/2017.
 */
public class SysNotificationDetailActivity extends HomeBaseActivity implements View.OnClickListener, IGetOrderDetailView, IGetOtherUserInfoView {

    private TextView tv_phone;
    private TextView tv_major;
    private TextView tv_company;
    private TextView tv_arrivePlace;
    private TextView tv_arriveTime;
    private TextView tv_tradePlace;
    private TextView tv_description;
    private TextView tv_sms;

    private String token;
    private String other_id;
    private String order_id;

    private String name;
    private String phone;
    private String major;
    private String company;
    private String arrivePlace;
    private String arriveTime;
    private String tradePlace;
    private String description;
    private String sms_content;

    private Button btn_chat;

    private GetOtherUserInfoPresenter otherUserInfoPresenter;
    private GetOrderDetailPresenter expressDetailPresenter;
    private static String tag = "sys_detail";

    @Override
    public void initView() {
        tv_phone = (TextView) findViewById(R.id.sys_notification_phone);
        tv_phone.setOnClickListener(this);
        tv_major = (TextView) findViewById(R.id.sys_notification_major);
        tv_company = (TextView) findViewById(R.id.sys_notification_company);
        tv_arrivePlace = (TextView) findViewById(R.id.sys_notification_take_place);
        tv_arriveTime = (TextView) findViewById(R.id.sys_notification_arrive_time);
        tv_tradePlace = (TextView) findViewById(R.id.sys_notification_trade_place);
        tv_description = (TextView) findViewById(R.id.sys_notification_descript);
        tv_sms = (TextView) findViewById(R.id.sys_notification_sms);
        tv_sms.setOnClickListener(this);

        btn_chat = (Button) findViewById(R.id.btn_sys_detail_chat);
        btn_chat.setOnClickListener(this);
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
        token = intent.getStringExtra(Constant.KEY_TOKEN);
        other_id = intent.getStringExtra(Constant.USER_ID);
        order_id = intent.getStringExtra(Constant.KEY_ORDER_ID);
        name = intent.getStringExtra(Constant.KEY_NAME);
        otherUserInfoPresenter = new GetOtherUserInfoPresenter(this);
        expressDetailPresenter = new GetOrderDetailPresenter(this);
        otherUserInfoPresenter.getUserInfo(token,other_id);
        expressDetailPresenter.getOrderDetail(token,order_id);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_notification_detail;
    }

    @Override
    public String getToolBarTitle() {
        if(name!=null){
            return name+"的快递详情";
        }
        return "快递详情";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sys_notification_phone:
                if(phone!=null){
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ phone));
                    startActivity(intent);
                }
                break;
            case R.id.btn_sys_detail_chat:
                if (RongIM.getInstance() != null) {
                    RongIM.getInstance().startPrivateChat(this, other_id, name);
                }
                break;
            case R.id.sys_notification_sms:
                Intent intent = new Intent(this,SMSCotentActivity.class);
                intent.putExtra(Constant.KEY_SMS,sms_content);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBindOrderDetail(OrderDetailData data) {
        company = data.getCompany();
        arrivePlace = data.getAddress();
        arriveTime = data.getTakeTime();
        tradePlace = data.getPlace();
        description = data.getDes();
        sms_content = data.getSms_content();

        tv_company.setText(company);
        tv_arrivePlace.setText(arrivePlace);
        tv_arriveTime.setText(arriveTime);
        tv_tradePlace.setText(tradePlace);
        tv_description.setText(description);
        if(sms_content.equals("")){
            Log.e(tag,"sms_content is null");
            tv_sms.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBind(OtherUserInfoData info) {
        name = info.getName();
        phone = info.getPhoneNum();
        major = info.getMajor();
        tv_phone.setText(phone);
        tv_major.setText(major);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}


