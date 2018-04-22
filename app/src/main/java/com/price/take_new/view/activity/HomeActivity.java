package com.price.take_new.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.takeretrofit.utils.ManagerData;
import com.price.take_new.AppActivity;
import com.price.take_new.BaseFragment;
import com.price.take_new.Constant;
import com.price.take_new.R;
import com.price.take_new.bean.UserInfo;
import com.price.take_new.presenter.GetUserInfoPresenter;
import com.price.take_new.rong.Rong;
import com.price.take_new.service.viewService.IGetUserInfoView;
import com.price.take_new.view.fragment.home.ChatFragment;
import com.price.take_new.view.fragment.home.HomeFragment;
import com.price.take_new.view.fragment.home.MainPackageFragment;
import com.price.take_new.view.fragment.home.PersonalSettingFragment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by intel on 2/15/2018.
 */

public class HomeActivity extends AppActivity {

    private WeakReference<BaseFragment> reference;

    private String TAG="HomeActivity";

    @Override
    protected BaseFragment getFirstFragment() {
        HomeFragment fragment = new HomeFragment();
        reference = new WeakReference<BaseFragment>(fragment);
        if(reference.get()!=null){
            return new HomeFragment();
        }else{
            return new HomeFragment();
        }
    }

//    private MainPackageFragment mainPackageFragment;
//    private ChatFragment chatFragment;
//    private PersonalSettingFragment personalSettingFragment;
//
//    private ViewPager mViewPager ;
//    private TabLayout mTabLayout;
//    private FragmentPagerAdapter mFragmentPagerAdapter;
//    private Fragment mConversationList;
//    private Fragment mConversationFragment = null;
//    private List<Fragment> mFragment = new ArrayList<>();
//
//    private String token;
//    private String ry_token;
//
//    private Boolean isAuth;
//
//    private int unReadMsgCount = 0;
//
////    private Parcelable state;
//
//    private GetUserInfoPresenter presenter;
//
//    private List<String> mTities = Arrays.asList("快递广场","消息","个人中心");
//    private int images[] ={R.drawable.express_plaza_selector, R.drawable.message_selector, R.drawable.personal_center_selector};
//
//    private static HomeFragment instance = new HomeFragment();
//    public static HomeFragment newInstance(){
//        if(instance == null){
//            instance = new HomeFragment();
//        }
//        return instance;
//    }
//
//    public View getTabView(int position) {
//        View v = LayoutInflater.from(this).inflate(R.layout.tab_custom, null);
//        TextView textView = (TextView) v.findViewById(R.id.tv_title);
//        ImageView imageView = (ImageView) v.findViewById(R.id.iv_icon);
//        textView.setText(mTities.get(position));
//        if(position == 1){
//            if(unReadMsgCount == 0) {
//                imageView.setImageResource(images[position]);
//            }else if(unReadMsgCount !=0){
//                imageView.setImageResource(images[2]);
//            }
//        }else{
//            imageView.setImageResource(images[position]);
//        }
//        //添加一行，设置颜色
//        textView.setTextColor(mTabLayout.getTabTextColors());//
//        return v;
//    }
//
//    private void initIndicator() {
//        //配置ViewPager的适配器
//        mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
//            @Override
//            public Fragment getItem(int position) {
//                return mFragment.get(position);
//            }
//
//            @Override
//            public int getCount() {
//                return mFragment.size();
//            }
//
//            @Override
//            public void destroyItem(ViewGroup container, int position, Object object) {
////                super.destroyItem(container, position, object);
//            }
//        };
//        mViewPager.setAdapter(mFragmentPagerAdapter);
//    }
//
//    @Override
//    protected BaseFragment getFirstFragment() {
//        return null;
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//        initView();
//    }
//
//    protected void initView() {
//        mViewPager = (ViewPager) findViewById(R.id.viewPager);
//
//        mainPackageFragment = new MainPackageFragment();
//        chatFragment = new ChatFragment();
//        personalSettingFragment = new PersonalSettingFragment();
//
//        mFragment.clear();
//        mFragment.add(mainPackageFragment);//加入第一页
//        mFragment.add(chatFragment);//加入第二页
//        mFragment.add(personalSettingFragment);//加入第三页
//
//        mTabLayout = (TabLayout) findViewById(R.id.home_tablayout);
//        mTabLayout.setupWithViewPager(mViewPager);
////        设置自定义视图
//        initIndicator();
//        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
//            TabLayout.Tab tab = mTabLayout.getTabAt(i);
//            tab.setCustomView(getTabView(i));
//        }
//        initData();
//    }
//
//    private void initData() {
//        token = ManagerData.getCachedToken(this);
//        ry_token = ManagerData.getCachedRongToken(this);
//        Rong.connect(ry_token);
//
//        presenter = new GetUserInfoPresenter(this);
//        presenter.getUserInfo(token,this);
//    }
//
//    @Override
//    public void showToast(String msg) {
//
//    }
//
//    @Override
//    public void bindData(UserInfo info) {
//        Rong.connect(ry_token);
//    }
//
//    @Override
//    public void hideLoading() {
//
//    }
//
//    @Override
//    public void showLoading() {
//
//    }
}
