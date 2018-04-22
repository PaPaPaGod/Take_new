package com.price.take_new.view.fragment.personal;

import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.takeretrofit.utils.ManagerData;
import com.price.take_new.BaseFragment;
import com.price.take_new.R;


/**
 * Created by intel on 2/18/2018.
 */

public class ManageTradeFragment extends BaseFragment {
    private EditText et_name;
    private EditText et_phone;
    private EditText et_trade;
    private AppCompatSpinner mSpinner;
    private SwitchCompat mSwitch;

    private String name;
    private String phone;
    private String trade;
    private String school;
    private boolean isDefault;

//    private static ManageTradeFragment instance = new ManageTradeFragment();
//    public static ManageTradeFragment newInstance(){
//        if(instance == null){
//            instance = new ManageTradeFragment();
//        }
//        return instance;
//    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        et_name = (EditText) view.findViewById(R.id.et_add_addr_receiver);
        et_phone = (EditText) view.findViewById(R.id.et_add_addr_phone);
        et_trade = (EditText) view.findViewById(R.id.et_add_addr);
        mSwitch = (SwitchCompat) view.findViewById(R.id.switch_add_addr);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    isDefault = true;
                }else{
                    isDefault = false;
                }
            }
        });
        mSpinner = (AppCompatSpinner) view.findViewById(R.id.add_addr_spinner);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                school = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        cacheData();
    }

    private void cacheData() {
        name = et_name.getText().toString();
        phone = et_phone.getText().toString();
        trade = et_trade.getText().toString();
        // TODO: 3/8/2018 使用数据库保存 TradeDefaultDatum
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_addr;
    }
}
