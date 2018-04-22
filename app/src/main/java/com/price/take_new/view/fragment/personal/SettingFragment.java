package com.price.take_new.view.fragment.personal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.takeretrofit.utils.ManagerData;
import com.price.take_new.BaseFragment;
import com.price.take_new.Constant;
import com.price.take_new.R;
import com.price.take_new.view.activity.LoginAcitivity;
import com.price.take_new.view.activity.PersonalMsgActivity;
import com.price.take_new.view.fragment.login.SetUserMsgFragment;


/**
 * Created by intel on 2/3/2018.
 */

public class SettingFragment extends BaseFragment {
    private Button btn_logout;
    private Button btn_suggest;
    private Button btn_info;
    private Button btn_addr;

    private Toolbar mToolbar;

//    private static SettingFragment instance = new SettingFragment();
//    public static SettingFragment getInstance(){
//        if(instance == null){
//            instance = new SettingFragment();
//        }
//        return instance;
//    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initToolbar(view);
        btn_addr = (Button) view.findViewById(R.id.setting_btn_addr);
        btn_addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_suggest = (Button) view.findViewById(R.id.setting_btn_suggest);
        btn_suggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFragment(FeedBackFragment.newInstance());
            }
        });

        btn_info = (Button) view.findViewById(R.id.setting_btn_info);
        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFragment(SetUserMsgFragment.newInstance());
            }
        });

        btn_logout = (Button) view.findViewById(R.id.setting_btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 3/16/2018 退出账号再次登录不显示的bug
                ManagerData.clearCachedData(getActivity());
                Intent intent = new Intent(getActivity(), LoginAcitivity.class);
                Intent intent1 = new Intent();
                getActivity().setResult(Activity.RESULT_OK,intent1);
                removeFragment();
                getActivity().finish();
                startActivity(intent);
            }
        });
    }

    private void initToolbar(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.setting_toolbar);
        getHoldingActivity().setSupportActionBar(mToolbar);

        toolBarTitle = (TextView) view.findViewById(R.id.toolbar_title);
        toolBarTitle.setText("设置");
        getHoldingActivity().getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setNavigationIcon(R.drawable.icon_back);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFragment();
            }
        });

        mRightImage = (ImageView) view.findViewById(R.id.toolbar_right_icon);
        mRightImage.setVisibility(View.GONE);
        mRightImage.setImageResource(R.drawable.icon_setting);
        mRightImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PersonalMsgActivity.class);
                intent.putExtra(Constant.FRAGMENT_NAME,Constant.SETTING);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }
}
