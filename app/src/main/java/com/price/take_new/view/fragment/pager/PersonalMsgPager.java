package com.price.take_new.view.fragment.pager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.takeretrofit.utils.ManagerData;
import com.price.take_new.BaseFragment;
import com.price.take_new.Constant;
import com.price.take_new.R;
import com.price.take_new.bean.UserInfo;
import com.price.take_new.presenter.GetUserInfoPresenter;
import com.price.take_new.service.viewService.IGetUserInfoView;
import com.price.take_new.view.activity.PersonalMsgActivity;


/**
 * Created by intel on 2/18/2018.
 */

public class PersonalMsgPager extends BaseFragment implements IGetUserInfoView {

    private TextView tv_name;
    private TextView tv_school;
    private TextView tv_major;
    private TextView tv_phone;
    private TextView tv_auth;
    private TextView tv_addr;

    private ImageView iv_auth;
    private ImageView iv_edit;

    private String token;

    private String name ;
    private String school ;
    private String stu_id ;
    private String sex;
    private String author;
    private String phone_num;
    private String major;
    private String trade;

    private GetUserInfoPresenter userInfoPresenter;

    private static PersonalMsgPager instance = new PersonalMsgPager();
    public static PersonalMsgPager newInstance(){
        if(instance == null){
            instance = new PersonalMsgPager();
        }
        return instance;
    }
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        userInfoPresenter = new GetUserInfoPresenter(this);
        token = ManagerData.getCachedToken(getActivity());
        tv_name = (TextView) view.findViewById(R.id.tv_pager_mymsg_name);
        tv_school = (TextView)view.findViewById(R.id.tv_pager_mymsg_school);
        tv_major = (TextView)view.findViewById(R.id.tv_pager_mymsg_major);
        tv_phone =(TextView) view.findViewById(R.id.tv_pager_mymsg_phone);
        tv_auth = (TextView)view.findViewById(R.id.tv_pager_mymsg_auth);
        tv_addr = (TextView)view.findViewById(R.id.tv_pager_mymsg_addr);

        iv_auth = (ImageView) view.findViewById(R.id.iv_pager_mymsg_auth);
        iv_auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),PersonalMsgActivity.class);
                intent.putExtra(Constant.FRAGMENT_NAME,Constant.AUTHORIZATION);
                startActivity(intent);
            }
        });

        iv_edit = (ImageView) view.findViewById(R.id.iv_pager_mymsg_addr);
        iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),PersonalMsgActivity.class);
                intent.putExtra(Constant.FRAGMENT_NAME,Constant.EDIT_TRADE);
                startActivity(intent);
            }
        });
        userInfoPresenter.getUserInfo(token,getActivity());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.pager_mymsg;
    }

    @Override
    public void showToast(String msg) {
        if(!TextUtils.isEmpty(msg))
            Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bindData(UserInfo info) {
        name = info.getName();
        phone_num = info.getPhoneNum();
        school = "仲恺农业工程学院";
        major = info.getMajor();
        author = info.getAuth();
        tv_name.setText(name);
        tv_phone.setText(phone_num);
        tv_school.setText(school);
        tv_major.setText(major);
        if(author.equals("0")){
            tv_auth.setText("未认证");
        }else{
            tv_auth.setText("已认证");
        }
        trade = ManagerData.getCachedTrade(getActivity());
        tv_addr.setText(trade);
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoading() {

    }
}
