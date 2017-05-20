package com.price.take_new.view.activity.home;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.price.take_new.Constant;
import com.price.take_new.HomeBaseActivity;
import com.price.take_new.R;
import com.price.take_new.presenter.PublishExpressPresenter;
import com.price.take_new.service.viewService.IPublishExpressView;
import com.price.take_new.view.myview.timepicker.TimePickerDialog;

import java.util.Calendar;

/**
 * Created by price on 2/20/2017.
 */

public class PublishExpressActivity extends HomeBaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, IPublishExpressView {
//    private static PublishExpressActivity instance = new PublishExpressActivity();

    private AppCompatSpinner spinner_company;

    private String take_time;

    private EditText et_take_time;
    private EditText et_take_place;
    private EditText et_des;
    private EditText et_trade;
    private EditText et_money;
    private EditText et_sms_content;

    private Button btn_take_time;

    private CheckBox cb_getMoney;

    private LinearLayout money_layout;

    private Button btn_publish;

    private String[] data = new String[8];

    private String token;

    private PublishExpressPresenter presenter;
    private TimePickerDialog timeDialog;

    private String[] getDatas() {
        data[0] = token;//token
        data[2] = et_des.getText().toString();  //des
        data[3] = et_take_place.getText().toString();   //take_place
        data[4] = et_trade.getText().toString();    //trade_place
        data[6] = take_time;    //take_time
        data[7] = et_sms_content.getText().toString(); //sms_content;
        return data;
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
        if(null!= intent){
            token = intent.getStringExtra(Constant.KEY_TOKEN);
            Log.e("publish",token);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_taken_time:
                Log.e("take","taken_time");
                // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
                showTimePick();
                break;
            case R.id.checkBox:
                Log.e("box", "onClick");
                if (cb_getMoney.isChecked()) {
                    money_layout.setVisibility(View.VISIBLE);
                } else {
                    money_layout.setVisibility(View.GONE);
                }
                break;
            case R.id.announce_button:
                Log.e("publish","publish");
                data = getDatas();
                if (cb_getMoney.isChecked()) {
                    data[5] = et_money.getText().toString();
                } else {
                    data[5] = "0";
                }
                boolean isOk = presenter.checkInput(data);
                if (isOk) {
                    if(TextUtils.isEmpty(data[7])){
                        AlertDialog dialog = new AlertDialog.Builder(this)
                                .setTitle("温馨提示").setMessage(R.string.publish_sms_null_tips)
                                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        presenter.publish(data[0], data[1], data[2], data[3], data[4], data[5], data[6],"");
                                        dialog.dismiss();

                                    }
                                }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).create();
                        dialog.show();
                    }else {
                        presenter.publish(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]);
                    }
                }
                break;
        }
    }

    private void showTimePick() {
        if (timeDialog == null) {
            TimePickerDialog.Builder builder = new TimePickerDialog.Builder(this);
            timeDialog = builder.setOnTimeSelectedListener(new TimePickerDialog.OnTimeSelectedListener() {
                @Override
                public void onTimeSelected(int[] times) {
                    btn_take_time.setText("您选择了 "+ times[0] + ":00 ~ "+times[1]+":00"+" 时间段");
                    take_time = times[0] + ":00 ~ "+times[1]+":00";
                }
            }).create();
        }
        timeDialog.show();
    }

    @Override
    public String getToolBarTitle() {
        return "发布快递";
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_publish_express;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        data[1] = this.getResources().getStringArray(R.array.express_company_array)[position];  //company
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void initView() {
        spinner_company = (AppCompatSpinner) findViewById(R.id.spinner_company);
        spinner_company.setOnItemSelectedListener(this);

        et_des = (EditText) findViewById(R.id.decription_editText);
        et_take_place = (EditText) findViewById(R.id.taken_location_editText);
        et_trade = (EditText) findViewById(R.id.trade_position_editText);
        et_money = (EditText) findViewById(R.id.amount);
        et_sms_content = (EditText) findViewById(R.id.sms_content);
//        et_take_time = (EditText) findViewById(R.id.taken_time_editText);
        btn_take_time = (Button) findViewById(R.id.btn_taken_time);
        btn_take_time.setText("请选择快递到达的时间");
        btn_take_time.setOnClickListener(this);

        cb_getMoney = (CheckBox) findViewById(R.id.checkBox);
        cb_getMoney.setOnClickListener(this);

        money_layout = (LinearLayout) findViewById(R.id.createOrder_getMoney);

        btn_publish = (Button) findViewById(R.id.announce_button);
        btn_publish.setOnClickListener(this);

        presenter = new PublishExpressPresenter(this);
    }

    @Override
    public void showToast(String msg,int code) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        if(code == Constant.SUCCESS_WITH_MSG){
            Intent intent = new Intent();
            setResult(Constant.RESULT_OK_FOR_PUBLISH,intent);
            finish();
        }
    }
}
