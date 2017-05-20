package com.price.take_new.view.activity.home.express;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.takeretrofit.bean.otheruserinfo.OtherUserInfoData;
import com.price.take_new.Constant;
import com.price.take_new.HomeBaseActivity;
import com.price.take_new.R;
import com.price.take_new.presenter.GetOtherUserInfoPresenter;
import com.price.take_new.presenter.ManagerExpressPresenter;
import com.price.take_new.service.viewService.IGetOtherUserInfoView;
import com.price.take_new.service.viewService.IManagerExpressView;
import com.price.take_new.view.activity.notification.SMSCotentActivity;

import io.rong.imkit.RongIM;

/**
 * 我的快递快递详情
 * Created by Administrator on 2016/9/3.
 */
public class MyExpressDetailActivity extends HomeBaseActivity implements View.OnClickListener, IManagerExpressView, IGetOtherUserInfoView {

    TextView tv_name;
    TextView tv_major;
    TextView tv_status;
    TextView tv_company;
    TextView tv_place;
    TextView tv_time;
    TextView tv_address;

    Button update;
    Button delete;
    Button chat;

    private LinearLayout ly_accepter_information;

    private String token;
    private String order_id;
    private String status;
    private String status_result;
    private String accepterName;
    private String accepterMajor;
    private String accepterId;
    private String company;
    private String place;
    private String time;
    private String address;
    private String sms_content;
//    private String
    private TextView hand_over_time;
    private TextView description;
    private TextView money;

    private TextView tv_sms;

    private ManagerExpressPresenter presenter;
    private GetOtherUserInfoPresenter otherUserInfoPresenter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_my_express_detail;
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
        if(intent!=null){
            token = intent.getStringExtra(Constant.KEY_TOKEN);
            order_id = intent.getStringExtra(Constant.KEY_ORDER_ID);
            status = intent.getStringExtra(Constant.KEY_STATUS);
            company = intent.getStringExtra(Constant.KEY_COMPANY);
            place = intent.getStringExtra(Constant.KEY_PLACE);
            time = intent.getStringExtra(Constant.KEY_TAKE_TIME);
            address = intent.getStringExtra(Constant.KEY_ADDRESS);
            sms_content = intent.getStringExtra(Constant.KEY_SMS);
            if (status.equals("2")){
                accepterName = intent.getStringExtra(Constant.KEY_ACCEPTER_NAME);
                accepterId  = intent.getStringExtra(Constant.KEY_ACCEPTER_ID);
                otherUserInfoPresenter = new GetOtherUserInfoPresenter(this);
                otherUserInfoPresenter.getUserInfo(token,accepterId);
            }
            Log.e("status",status);
            switch (status){
                case "0":
                    status_result = "进行中，等待接受";
                    break;
                case "2":
                    status_result ="已接受";
                    break;
                case "1":
                    status_result = "status = 1";
                    break;
                default:
                    status_result = "";
                    break;
            }
            Log.e("result_status",status_result==null?"yes":"no");
        }
    }

    private void setDatas(Intent intent) {
        tv_company.setText(company);
        tv_place.setText(place);
        tv_time.setText(time);
        tv_address.setText(address);
        tv_status.setText(status_result);
        if(status.equals("2")) {
            tv_name.setText(accepterName);
        }
    }

    @Override
    public void initView() {
        ly_accepter_information = (LinearLayout) findViewById(R.id.accepter_information);

        tv_name = (TextView) findViewById(R.id.express_my_detail_name);
        tv_major = (TextView) findViewById(R.id.express_my_detail_major);

        tv_company = (TextView) findViewById(R.id.express_my_detail_company);
        tv_place = (TextView) findViewById(R.id.express_my_detail_taken_location);
        tv_time = (TextView) findViewById(R.id.express_my_detail_taken_time);
//        hand_over_time = (TextView) findViewById(R.id.express_my_detail_finish_time);
        tv_address = (TextView) findViewById(R.id.express_my_detail_finish_location);
        tv_status = (TextView) findViewById(R.id.express_my_detail_status);
//        description = (TextView) findViewById(R.id.express_my_detail_description);
//        description = (TextView) findViewById(R.id.express_my_detail_bounty);
        tv_sms = (TextView) findViewById(R.id.express_my_detail_sms);
        tv_sms.setOnClickListener(this);

        update = (Button) findViewById(R.id.btn_update);
        delete = (Button) findViewById(R.id.btn_delete);
        chat = (Button) findViewById(R.id.btn_send_to_helper);
        if(!status.equals("2")){
            ly_accepter_information.setVisibility(View.GONE);
            chat.setVisibility(View.GONE);
        }
        update.setOnClickListener(this);
        delete.setOnClickListener(this);
        delete.setClickable(true);
        chat.setOnClickListener(this);

        presenter = new ManagerExpressPresenter(this);
        setDatas(getIntent());
    }

    @Override
    public String getToolBarTitle() {
        return "快递详情";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_update:
                if(status.equals("0")){
                    Intent intent = new Intent(this,UpdateExpressActivity.class);
                    intent.putExtra(Constant.KEY_TOKEN,token);
                    intent.putExtra(Constant.KEY_ORDER_ID,order_id);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MyExpressDetailActivity.this,"该订单已被接单，不能被修改",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_delete:
                if(status.equals("0")) {
                    Log.e("order_id",order_id);
                    presenter.showDialog();
                }else {
                    Toast.makeText(MyExpressDetailActivity.this, "该订单已被接单，不能被删除", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_send_to_helper:
                if (RongIM.getInstance() != null) {
                    RongIM.getInstance().startPrivateChat(this, accepterId, accepterName);
                }
                this.finish();
                break;
            case R.id.express_my_detail_sms:
                Intent intent = new Intent(this,SMSCotentActivity.class);
                intent.putExtra(Constant.KEY_SMS,sms_content);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case Constant.SUCCESS_BACK:
                finish();
                break;
        }
    }

    @Override
    public void showToast(String msg, int code) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        if(code == Constant.SUCCESS_WITH_MSG){
            finish();
            setResult(Constant.SUCCESS_REFRESH);
        }
    }

    @Override
    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认要删除吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.delete(token,order_id);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public void onBind(OtherUserInfoData info) {
        accepterMajor = info.getMajor();
        Log.e("major",accepterMajor);
        if(accepterMajor!=null && status.equals("2")){
            tv_major.setText(accepterMajor);
        }
    }

    @Override
    public void showToast(String msg) {

    }
}
