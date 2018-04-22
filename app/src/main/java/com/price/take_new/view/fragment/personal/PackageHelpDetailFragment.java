package com.price.take_new.view.fragment.personal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.takeretrofit.bean.otheruserinfo.OtherUserInfoData;
import com.example.takeretrofit.utils.ManagerData;
import com.price.take_new.BaseFragment;
import com.price.take_new.Constant;
import com.price.take_new.R;
import com.price.take_new.bean.UserInfo;
import com.price.take_new.presenter.AcceptExpressPresenter;
import com.price.take_new.presenter.GetOtherUserInfoPresenter;
import com.price.take_new.presenter.GetUserInfoPresenter;
import com.price.take_new.service.viewService.AcceptExpressView;
import com.price.take_new.service.viewService.IGetOtherUserInfoView;
import com.price.take_new.service.viewService.IGetUserInfoView;
import com.price.take_new.view.activity.PersonalMsgActivity;

import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;


/**
 * Created by intel on 2/19/2018.
 */

public class PackageHelpDetailFragment extends BaseFragment implements IGetOtherUserInfoView, IGetUserInfoView {
    private TextView tv_company;
    private TextView tv_addr;
    private TextView tv_trade;
    private TextView tv_remarks;
    private TextView tv_desc;
    private TextView tv_reward;
    private TextView tv_isInschool;
    private TextView tv_sms;
    private TextView tv_tbname;
    private TextView tv_phone;

    private LinearLayout ly_reward;

    private Button btn_chat;

    private String token;
    private String company;
    private String addr;
    private String trade;
    private String remarks;
    private String desc;
    private String reward;
    private String order_id;
    private String phone;
    private int isIN;
    private String tbname;
    private String sms;
    private String price;
    private String small_reward;
    private String name;
    private String user_id;

    private OtherUserInfoData info;
    private UserInfo myInfo;
    private UserInfo otherInfo;

    private GetOtherUserInfoPresenter otherUserInfoPresenter;
    private GetUserInfoPresenter userInfoPresenter;

    private Toolbar mToolbar;

    private static PackageHelpDetailFragment instance = new PackageHelpDetailFragment();
    public static PackageHelpDetailFragment newInstance() {
        if (instance == null) {
            instance = new PackageHelpDetailFragment();
        }
        return instance;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initToolbar(view);
        otherUserInfoPresenter = new GetOtherUserInfoPresenter(this);
        token = ManagerData.getCachedToken(getActivity());
        tv_company = (TextView) view.findViewById(R.id.help_detail_company);
        tv_addr = (TextView) view.findViewById(R.id.help_detail_addr);
        tv_trade = (TextView) view.findViewById(R.id.help_detail_trade);
        tv_remarks = (TextView) view.findViewById(R.id.help_detail_remarks);
        tv_desc = (TextView) view.findViewById(R.id.help_detail_desc);
        tv_reward = (TextView) view.findViewById(R.id.help_detail_reward);
        tv_isInschool = (TextView) view.findViewById(R.id.help_detail_atschool);
        tv_phone = (TextView) view.findViewById(R.id.help_detail_phone);
        tv_tbname = (TextView) view.findViewById(R.id.help_detail_tbname);
        tv_sms = (TextView) view.findViewById(R.id.help_detail_sms);

        btn_chat = (Button) view.findViewById(R.id.help_detail_chat);
        btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo chat with him
                toChat();
            }
        });
        ly_reward = (LinearLayout) view.findViewById(R.id.help_detail_ly_reward);

        otherUserInfoPresenter = new GetOtherUserInfoPresenter(this);
//        userInfoPresenter.getUserInfo(token,getActivity());
        showDetail();
    }

    private void showDetail() {
        Bundle bundle = getArguments();
        if(bundle!=null){
            company = bundle.getString(Constant.KEY_COMPANY);
            addr = bundle.getString(Constant.KEY_ADDRESS);
            trade = bundle.getString(Constant.KEY_PLACE);
//            remarks = bundle.getString(Constant.KEY_COMPANY);
//            reward = bundle.getString(Constant.KEY_COMPANY);
            price = bundle.getString(Constant.KEY_PRICE);
            small_reward = bundle.getString(Constant.KEY_SMALL_REWARD);
            isIN = bundle.getInt(Constant.AT_SCHOOL);
            sms = bundle.getString(Constant.KEY_SMS);
            tbname = bundle.getString(Constant.TB_NAME);
            name = bundle.getString(Constant.KEY_NAME);
            user_id = bundle.getString(Constant.KEY_USER_ID);
            desc = bundle.getString(Constant.KEY_DES);

            tv_company.setText(company);
            tv_addr.setText(addr);
            tv_trade.setText(trade);
            tv_tbname.setText(tbname);
//            tv_remarks.setText(remarks);
            tv_reward.setText(reward);
            if(isIN==0){
                tv_isInschool.setText("不在校");
            }else{
                tv_isInschool.setText("在校");
            }
            tv_desc.setText(desc);
            tv_sms.setText(sms);
            setToolbarTitle(name+"的快递");
        }
//        otherUserInfoPresenter.getUserInfo(token,poster_id);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_help_detail;
    }

    @Override
    public void onBind(OtherUserInfoData info) {
        this.info = info;
        otherInfo = new UserInfo(Long.parseLong(info.getId()),
                info.getPhoneNum(),
                info.getName(),
                info.getSex(),
                info.getSchoolName(),
                info.getMajor(),
                "","","");
        tv_phone.setText(info.getPhoneNum());
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bindData(UserInfo info) {
        myInfo = info;
//        setToolbarTitle(name+"的快递");
    }

    private void initToolbar(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.help_detail_toolbar);
        getHoldingActivity().setSupportActionBar(mToolbar);

        toolBarTitle = (TextView) view.findViewById(R.id.toolbar_title);
        getHoldingActivity().getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setNavigationIcon(R.drawable.icon_back);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFragment();
            }
        });

        mRightImage = (ImageView) view.findViewById(R.id.toolbar_right_icon);
        mRightImage.setImageResource(R.drawable.icon_setting);
        mRightImage.setVisibility(View.GONE);
    }

    private void setToolbarTitle(String title){
        if(toolBarTitle!=null){
            toolBarTitle.setText(title);
        }
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoading() {

    }

    private void toChat(){
        if (RongIM.getInstance() != null) {
            RongIM.getInstance().startPrivateChat(getActivity(), user_id, name);
        }
        removeFragment();
    }
}


