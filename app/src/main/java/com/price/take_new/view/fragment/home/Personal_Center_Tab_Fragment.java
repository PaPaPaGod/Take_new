package com.price.take_new.view.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.takeretrofit.utils.ManagerData;
import com.price.take_new.BaseActivity;
import com.price.take_new.BaseFragment;
import com.price.take_new.Constant;
import com.price.take_new.R;
import com.price.take_new.bean.UserInfo;
import com.price.take_new.presenter.GetUserInfoPresenter;
import com.price.take_new.service.viewService.IGetUserInfoView;
import com.price.take_new.utils.ManagerUserInfo;
import com.price.take_new.view.activity.SplashActivity;
import com.price.take_new.view.activity.home.AuthoActivity;
import com.price.take_new.view.activity.home.Feed_Back_Activity;
import com.price.take_new.view.activity.home.Activity_Personal_Document;
import com.price.take_new.view.activity.home.HomeActivity;
import com.price.take_new.view.activity.home.express.ExpressHelpActivity;
import com.price.take_new.view.activity.home.express.MyExpressActivity;

import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * Created by lenovo on 2016/7/19.
 */
public class Personal_Center_Tab_Fragment extends BaseFragment implements IGetUserInfoView {
    private Button personalDocumentButton;
    private Button expressHelpButton;
    private Button myExpressButton;
    private Button feedbackButton;
    private Button quitButton;
    private Button autho;

    private TextView auth;
    private TextView userName;
    private TextView schoolName;
    private ImageView portrait;

    private String token;
    String name ;
    String school ;
    String stu_id ;
    String sex;
    String author;

    private UserInfo info;

    private GetUserInfoPresenter presenter;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public static Personal_Center_Tab_Fragment instance = new Personal_Center_Tab_Fragment();//单例模式

    public static Personal_Center_Tab_Fragment getInstance() {
        if (instance == null) {
            instance = new Personal_Center_Tab_Fragment();
        }
        return instance;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if(bundle!=null){
            token = bundle.getString(Constant.KEY_TOKEN);
            Log.e("tab_fragment",token);
        }

        presenter = new GetUserInfoPresenter(this);

        userName = (TextView) view.findViewById(R.id.userName);
        schoolName = (TextView) view.findViewById(R.id.school);
        auth = (TextView) view.findViewById(R.id.authenticated);
        portrait = (ImageView) view.findViewById(R.id.avatar);

        personalDocumentButton = (Button) view.findViewById(R.id.personal_ducument);
        personalDocumentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Activity_Personal_Document.class);
                if(info!=null){
                    Bundle userInfo = new Bundle();
//                    userInfo.putSerializable(Constant.USER_INFO,info);
                    intent.putExtras(userInfo);
                }
                intent.putExtra(Constant.KEY_TOKEN, token);
                startActivity(intent);
            }
        });

        expressHelpButton = (Button) view.findViewById(R.id.express_help);
        expressHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ExpressHelpActivity.class);
                intent.putExtra(Constant.KEY_TOKEN,token);
                startActivity(intent);
            }
        });

        myExpressButton = (Button) view.findViewById(R.id.my_express);
        myExpressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MyExpressActivity.class);
                intent.putExtra(Constant.KEY_TOKEN,token);
                startActivity(intent);
            }
        });

        feedbackButton = (Button) view.findViewById(R.id.feedback);
        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Feed_Back_Activity.class);
                intent.putExtra(Constant.KEY_TOKEN,token);
                startActivity(intent);
            }
        });

        autho = (Button) view.findViewById(R.id.autho);
        autho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AuthoActivity.class);
                intent.putExtra(Constant.KEY_TOKEN,token);
                startActivity(intent);
            }
        });

        quitButton = (Button) view.findViewById(R.id.quit);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:退出应用
                getActivity().finish();
                ManagerData.cachePhoneNum(getActivity(),null);
                ManagerData.cacheToken(getActivity(),null);
                ManagerData.cacheRongToken(getActivity(),null);
                ManagerUserInfo.clear(getActivity());
                ManagerData.cacheAuth(getActivity(),false);
                RongIM.getInstance().logout();
                RongIM.getInstance().disconnect();
                RongIM.getInstance().clearConversations();
                startActivity(new Intent(getActivity(), SplashActivity.class));
                getHoldingActivity().finish();
            }
        });

        presenter.getUserInfo(token,getActivity());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.personal_center_tab;
    }

    private void setPortrait(String sex){
        if(!TextUtils.isEmpty(sex)){
            if(sex.equals("男") ){
                portrait.setImageResource(R.mipmap.male);
            }else {
                portrait.setImageResource(R.mipmap.female);
            }
        }else
            return;
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bindData(UserInfo info) {
        name = info.getName();
        school = info.getSchool();
        stu_id = info.getStuId();
        sex = info.getSex();
        author = info.getAuth();
        if(author.equals("0")){
            author = "未认证";
        }else if(author.equals("1")){
            author = "已认证";
        }else if(info.getStuId()!=null){
            author = "认证中";
        }
        userName.setText(name);
        schoolName.setText(school);
        auth.setText(author);
        setPortrait(sex);
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoading() {

    }
}
