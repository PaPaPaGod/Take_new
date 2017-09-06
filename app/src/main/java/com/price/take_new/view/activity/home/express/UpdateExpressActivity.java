package com.price.take_new.view.activity.home.express;

import android.content.Intent;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.price.take_new.Constant;
import com.price.take_new.HomeBaseActivity;
import com.price.take_new.R;
import com.price.take_new.presenter.ManagerExpressPresenter;
import com.price.take_new.service.viewService.IManagerExpressView;

/**
 * 更新快递订单页面
 * Created by Administrator on 2016/9/4.
 */
public class UpdateExpressActivity extends HomeBaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, IManagerExpressView {
    private AppCompatSpinner spinner_company;
    private EditText et_take_time;
    private EditText et_take_place;
    private EditText et_des;
    private EditText et_trade;
    private EditText et_money;

    private CheckBox cb_getMoney;

    private LinearLayout money_layout;

    private Button btn_publish;

    private String[] data;

    private String token;
    private String order_id;
    private String company;
    private String address;
    private String take_time;
    private String place;
    private String description;
    private String money;

    private ManagerExpressPresenter presenter;

    @Override
    public void initView() {
        spinner_company = (AppCompatSpinner) findViewById(R.id.spinner_company);
        spinner_company.setOnItemSelectedListener(this);

        et_des = (EditText) findViewById(R.id.decription_editText);
        et_des.setText(description);

        et_take_place = (EditText) findViewById(R.id.taken_location_editText);
        et_take_place.setText(address);

        et_trade = (EditText) findViewById(R.id.trade_position_editText);
        et_trade.setText(place);

        et_money = (EditText) findViewById(R.id.amount);
        et_money.setText(money);
//        et_take_time = (EditText) findViewById(R.id.taken_time_editText);

        cb_getMoney = (CheckBox) findViewById(R.id.checkBox);
        cb_getMoney.setOnClickListener(this);

        money_layout = (LinearLayout) findViewById(R.id.createOrder_getMoney);

        btn_publish = (Button) findViewById(R.id.announce_button);

        btn_publish.setOnClickListener(this);

        presenter = new ManagerExpressPresenter(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_publish_express;
    }

    @Override
    public String getToolBarTitle() {
        return "更新快递信息";
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
        if(intent!=null){
            token = getIntent().getStringExtra(Constant.KEY_TOKEN);
            order_id = getIntent().getStringExtra(Constant.KEY_ORDER_ID);
            company = intent.getStringExtra(Constant.KEY_COMPANY);
            place = intent.getStringExtra(Constant.KEY_PLACE);
            take_time = intent.getStringExtra(Constant.KEY_TAKE_TIME);
            address = intent.getStringExtra(Constant.KEY_ADDRESS);
            description = intent.getStringExtra(Constant.KEY_DESCRIPTION);
            money = intent.getStringExtra(Constant.KEY_PRICE);
            data = new String[7];
        }
    }

    private String[] getDatas() {
        data[0] = token;//token
        data[2] = et_des.getText().toString();  //des
        data[3] = et_take_place.getText().toString();   //take_place
        data[4] = et_trade.getText().toString();    //trade_place
//        data[6] = et_take_time.getText().toString();    //take_time
        return data;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.checkBox:
                if(!cb_getMoney.isChecked()){
                    money_layout.setVisibility(View.INVISIBLE);
                }
                else
                    money_layout.setVisibility(View.VISIBLE);
                break;
            case R.id.announce_button:
                data = getDatas();
                if(cb_getMoney.isChecked()){
                    data[5] = et_money.getText().toString();
                }else{
                    data[5] = "0";
                }
                presenter.update(token,order_id, data[1], data[2], data[3], data[4], data[5], data[6]);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        data[1] = this.getResources().getStringArray(R.array.express_company_array)[position];  //company
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void showToast(String msg, int code) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        if(code == Constant.SUCCESS_WITH_MSG){
            finish();
            setResult(Constant.SUCCESS_BACK);
        }
    }

    @Override
    public void showDialog() {

    }
}
