package com.price.take_new.view.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
import com.price.take_new.view.fragment.pager.MyHelpPackagePager;
import com.price.take_new.view.fragment.pager.MyPackagePager;
import com.price.take_new.view.fragment.pager.PersonalMsgPager;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by intel on 2/18/2018.
 */

public class PersonalSettingFragment extends BaseFragment implements IGetUserInfoView {
    private static final String TAG = "MainPackageFragment";
    private static final int FINISH = 100;

    private ViewPager mViewPager;
    private TabLayout mTablayout;
    private ImageView mPortrait;

    private List<Fragment> mFragment = new ArrayList<>();

    private FragmentPagerAdapter mFragmentPagerAdapter;

    private String token;
    private boolean isMale = false;
    private boolean isAuth = false;

    private PersonalMsgPager personalMsgPager;
    private MyHelpPackagePager myHelpPackagePager;
    private MyPackagePager myPackagePager;

    private GetUserInfoPresenter userInfoPresenter;

    private static PersonalSettingFragment instance = new PersonalSettingFragment();
    public static PersonalSettingFragment newInstance(){
        if(instance == null){
            instance = new PersonalSettingFragment();
        }
        return instance;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initToolbar(view);
        token = ManagerData.getCachedToken(getActivity());
        userInfoPresenter = new GetUserInfoPresenter(this);
        mPortrait = (ImageView) view.findViewById(R.id.iamge_personal_setting);
        mTablayout = (TabLayout) view.findViewById(R.id.tablayout_personal_setting);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager_personal_setting);

        personalMsgPager = new PersonalMsgPager();
        myHelpPackagePager = new MyHelpPackagePager();
        myPackagePager = new MyPackagePager();

        initIndicator();
        initTablayout();

        userInfoPresenter.getUserInfo(token,getActivity());
    }

    private void initTablayout() {
        mTablayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal_setting;
    }

    private void initIndicator() {
        if (mFragment.size()==0){
            mFragment.add(personalMsgPager);
            mFragment.add(myHelpPackagePager);
            mFragment.add(myPackagePager);
        }
        //配置ViewPager的适配器
        mFragmentPagerAdapter = new FragmentPagerAdapter(getHoldingActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position){
                    case 0:
                        return "个人信息";
                    case 1:
                        return "代拿快递";
                    case 2:
                        return "我的快递";
                    default:
                        return "unknown";
                }
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
            }
        };
        mViewPager.setAdapter(mFragmentPagerAdapter);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bindData(UserInfo info) {
        String sex = info.getSex();
        if (sex.equals("男")){
            mPortrait.setImageResource(R.drawable.portrait_male);
        }else{
            mPortrait.setImageResource(R.drawable.portrait_female);
        }
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoading() {

    }

    private void initToolbar(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.personal_tab_toolbar);
        getHoldingActivity().setSupportActionBar(mToolbar);

        toolBarTitle = (TextView) view.findViewById(R.id.toolbar_title);
        toolBarTitle.setText("市场");
        getHoldingActivity().getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setNavigationIcon(null);

        mRightImage = (ImageView) view.findViewById(R.id.toolbar_right_icon);
        mRightImage.setImageResource(R.drawable.icon_setting);
        mRightImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PersonalMsgActivity.class);
                intent.putExtra(Constant.FRAGMENT_NAME,Constant.SETTING);
                startActivityForResult(intent,FINISH);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Log.e(TAG,"personal setting result ok");
            removeFragment();
            getHoldingActivity().finish();
        }
    }
}
