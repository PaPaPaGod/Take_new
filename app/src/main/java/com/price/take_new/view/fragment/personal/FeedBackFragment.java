package com.price.take_new.view.fragment.personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.takeretrofit.utils.ManagerData;
import com.price.take_new.BaseFragment;
import com.price.take_new.Constant;
import com.price.take_new.R;
import com.price.take_new.presenter.FeedBackPresenter;
import com.price.take_new.service.viewService.IFeedBackView;


/**
 * Created by intel on 2/18/2018.
 */

public class FeedBackFragment extends BaseFragment implements IFeedBackView {
    private EditText et_feedback;
    private Button btn_feedback;

    private String token;

    private FeedBackPresenter feedBackPresenter;

    private static FeedBackFragment instance = new FeedBackFragment();
    public static FeedBackFragment newInstance(){
        if(instance == null){
            instance = new FeedBackFragment();
        }
        return instance;
    }
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initToolbar(view);
        token = ManagerData.getCachedToken(getActivity());
        feedBackPresenter = new FeedBackPresenter(this);
        et_feedback = (EditText) view.findViewById(R.id.et_feedback);
        btn_feedback = (Button) view.findViewById(R.id.btn_feedback);
        btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feedback = et_feedback.getText().toString();
                if(TextUtils.isEmpty(feedback)){
                    Toast.makeText(getActivity(),"请输入您的意见",Toast.LENGTH_SHORT).show();
                    return;
                }
                feedBackPresenter.feedBack(token,feedback);
            }
        });
    }

    private void initToolbar(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.feedback_toolbar);
        getHoldingActivity().setSupportActionBar(mToolbar);

        toolBarTitle = (TextView) view.findViewById(R.id.toolbar_title);
        toolBarTitle.setText("提交建议");
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
    protected int getLayoutId() {
        return R.layout.fragment_feedback;
    }

    @Override
    public void showToast(String msg, int code) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
        if(code == Constant.SUCCESS_WITH_MSG){
            removeFragment();
        }
    }
}
