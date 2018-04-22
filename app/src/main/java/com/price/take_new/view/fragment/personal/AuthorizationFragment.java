package com.price.take_new.view.fragment.personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.takeretrofit.utils.ManagerData;
import com.price.take_new.BaseFragment;
import com.price.take_new.Constant;
import com.price.take_new.R;
import com.price.take_new.presenter.AuthPresenter;
import com.price.take_new.service.viewService.IAuthView;
import com.price.take_new.view.activity.PersonalMsgActivity;


/**
 * Created by intel on 2/18/2018.
 */

public class AuthorizationFragment extends BaseFragment implements IAuthView {
    private EditText et_id;
    private EditText et_pass;
    private Button btn_submit;
    private ImageView iv_status;
    private TextView tv_status;

    private RelativeLayout rl;
    private RelativeLayout rl_pic;

    private String token;
    private int status;

    private AuthPresenter authPresenter;


    private static AuthorizationFragment instance = new AuthorizationFragment();
    public static AuthorizationFragment newInstance(){
        if(instance == null){
            instance = new AuthorizationFragment();
        }
        return instance;
    }
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initToolbar(view);
        token = ManagerData.getCachedToken(getActivity());
        status = Constant.STATUS_FINISH;
        authPresenter = new AuthPresenter(this);
        Bundle bundle = getArguments();
        if(bundle!=null){
            status = bundle.getInt(Constant.KEY_AUTHORISE_STATUS);
        }
        iv_status = (ImageView) view.findViewById(R.id.iv_authorise_status);
        tv_status = (TextView) view.findViewById(R.id.tv_authorise_status);
        rl = (RelativeLayout) view.findViewById(R.id.ly_authorise);
        rl_pic = (RelativeLayout) view.findViewById(R.id.autho_rl_pic);
        et_id = (EditText) view.findViewById(R.id.et_autho_id);
        et_pass = (EditText) view.findViewById(R.id.et_autho_password);
        btn_submit = (Button) view.findViewById(R.id.btn_autho_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = et_id.getText().toString();
                String pass = et_pass.getText().toString();
                if(isNull(id,pass)){
                    Toast.makeText(getActivity(),"请输入完整信息",Toast.LENGTH_SHORT).show();
                    return;
                }
                authPresenter.auth(token,id,pass);
            }
        });
        switch (status){
            case Constant.STATUS_NOT:
                rl_pic.setVisibility(View.GONE);
                rl.setVisibility(View.VISIBLE);
                break;
            case Constant.STATUS_ONGOING:
                rl_pic.setVisibility(View.VISIBLE);
                iv_status.setBackgroundResource(R.drawable.authorise_waiting);
                tv_status.setText("正在审核中...");
                rl.setVisibility(View.GONE);
                break;
            case Constant.STATUS_FINISH:
                rl_pic.setVisibility(View.VISIBLE);
                iv_status.setBackgroundResource(R.drawable.authorise_finish);
                tv_status.setText("已通过认证，你可以去帮助同校的小伙伴拿快递啦");
                rl.setVisibility(View.GONE);
                break;
        }
    }

    private boolean isNull(String id, String pass) {
        if(TextUtils.isEmpty(id)){
            return true;
        }
        if(TextUtils.isEmpty(pass)){
            return true;
        }
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_authorise;
    }

    private void initToolbar(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.autho_toolbar);
        getHoldingActivity().setSupportActionBar(mToolbar);

        toolBarTitle = (TextView) view.findViewById(R.id.toolbar_title);
        toolBarTitle.setText("个人认证");
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
    }

    @Override
    public void showToast(String msg, int code) {
        if(code == Constant.SUCCESS_WITH_MSG){
            Toast.makeText(getActivity(),"已提交审核，请耐心等待",Toast.LENGTH_LONG).show();
            removeFragment();
            return;
        }
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }
}
