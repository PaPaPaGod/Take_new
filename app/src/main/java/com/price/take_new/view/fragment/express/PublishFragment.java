package com.price.take_new.view.fragment.express;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.takeretrofit.utils.ManagerData;
import com.price.take_new.BaseFragment;
import com.price.take_new.Constant;
import com.price.take_new.R;
import com.price.take_new.presenter.PublishExpressPresenter;
import com.price.take_new.service.viewService.IPublishExpressView;


/**
 * Created by intel on 2/19/2018.
 */

public class PublishFragment extends BaseFragment implements IPublishExpressView {
    private static final String TAG = "PublishFragment";
    private Toolbar mToolbar;

    private EditText et_tbname;
    private EditText et_trade;
    private EditText et_addr;
    private EditText et_des;
    private EditText et_sms;
    private EditText et_reward_money;
    private EditText et_reward_goods;

    private LinearLayout ll_money;
    private LinearLayout ll_goods;

    private SwitchCompat mSwitch;
    private AppCompatSpinner mSpinner;

    private RadioGroup rg_atschool;
    private RadioGroup rg_size;
    private RadioGroup rg_reward;

    private Button btn_publish;

    private String token;
    private int size;
    private int atschool;
    private String tbname;
    private String trade;
    private String addr;
    private String des;
    private String sms;
    private String company;
    private String reward;

    private Boolean rewardType;

    private PublishExpressPresenter presenter;
    
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initToolbar(view);
        presenter = new PublishExpressPresenter(this);
        token = ManagerData.getCachedToken(getActivity());
        et_addr = (EditText) view.findViewById(R.id.et_publish_address);
        et_tbname = (EditText) view.findViewById(R.id.et_publish_tbname);
        et_trade = (EditText) view.findViewById(R.id.et_publish_trade);
        et_des = (EditText) view.findViewById(R.id.et_publish_des);
        et_sms = (EditText) view.findViewById(R.id.et_publish_sms);
        et_reward_goods = (EditText) view.findViewById(R.id.et_publish_goods);
        et_reward_money = (EditText) view.findViewById(R.id.et_publish_money);

        ll_goods = (LinearLayout) view.findViewById(R.id.ll_publish_goods);
        ll_money = (LinearLayout) view.findViewById(R.id.ll_publish_money);

        mSpinner = (AppCompatSpinner) view.findViewById(R.id.spinner_publish_companny);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                company = getActivity().getResources().getStringArray(R.array.express_company_array)[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mSwitch = (SwitchCompat) view.findViewById(R.id.switch_publish_default);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    trade = ManagerData.getCachedTrade(getActivity());
                }
            }
        });

        rg_size = (RadioGroup) view.findViewById(R.id.rg_size);
        rg_size.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_publish_small:
                        size = 1;
                        break;
                    case R.id.rb_publish_mid:
                        size = 2;
                        break;
                    case R.id.rb_publish_big:
                        size = 3;
                        break;
                }
            }
        });

        rg_atschool = (RadioGroup) view.findViewById(R.id.rg_atschool);
        rg_atschool.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_publish_in:
                        atschool = 1;
                        break;
                    case R.id.rb_publish_notin:
                        atschool = 0;
                        break;
                }
            }
        });

        rg_reward = (RadioGroup) view.findViewById(R.id.rg_reward);
        rg_reward.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_publish_money:
                        ll_goods.setVisibility(View.GONE);
                        ll_money.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_publish_goods:
                        ll_money.setVisibility(View.GONE);
                        ll_goods.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        btn_publish = (Button) view.findViewById(R.id.btn_publish);
        btn_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                presenter.publish(token,company,des,addr,trade,0,
                        tbname,size,atschool,sms,reward,rewardType);
            }
        });
    }

    private void getData() {
        des = et_des.getText().toString();
        addr = et_addr.getText().toString();
        trade = et_trade.getText().toString();
        tbname = et_tbname.getText().toString();
        sms = et_sms.getText().toString();
        if(ll_money.getVisibility()==View.VISIBLE){
            reward = et_reward_money.getText().toString();
            rewardType = true;
        }else if (ll_goods.getVisibility()==View.VISIBLE){
            reward = et_reward_goods.getText().toString();
            rewardType = false;
        }else {
            rewardType = false;
            reward = "";
        }
    }

    private void initToolbar(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.publish_toolbar);
        getHoldingActivity().setSupportActionBar(mToolbar);

        toolBarTitle = (TextView) view.findViewById(R.id.toolbar_title);
        toolBarTitle.setText("填写快递信息");
        getHoldingActivity().getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setNavigationIcon(R.drawable.icon_back);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFragment();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_publish;
    }

    @Override
    public void showToast(String msg, int code) {
        Log.e(TAG,msg);
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
        if(code == Constant.SUCCESS_WITH_MSG){
            Intent intent = new Intent();
            getActivity().setResult(Constant.RESULT_OK_FOR_PUBLISH,intent);
            removeFragment();
        }
    }
}


