package com.price.take_new;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.price.take_new.view.activity.PersonalMsgActivity;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public abstract class BaseFragment extends Fragment {
    private String TAG = "BaseFragment";

    private View rootView;

    protected BaseActivity mActivity;

    protected boolean isOnCreate = false;

    protected abstract void initView(View view, Bundle savedInstanceState);

    //获取布局文件ID
    protected abstract int getLayoutId();

    protected String makeFragmentName(int viewId, int index) {
        return "android:switcher:" + viewId + ":" + index;
    }

    protected Toolbar mToolbar;
    protected TextView toolBarTitle;
    protected ImageView mRightImage;

//    public void update(int position, String str) {
//        Fragment fragment = getHoldingActivity().findFragmentByTag(mTagList.get(position));
////        if (fragment == null) return;
////        if (fragment instanceof BaseFragment) {
////            ((BaseFragment)fragment).update(str);
////        }
////        notifyDataSetChanged();
//    }

//    public void switchContent(Fragment from, Fragment to, int position) {
//        if (mContent != to) {
//            mContent = to;
//            FragmentTransaction transaction = getHoldingActivity().getSupportFragmentManager().beginTransaction();
//            if (!to.isAdded()) { // 先判断是否被add过
//                transaction.hide(from)
//                        .add(R.id.fra_counselor, to, tags[position]).commit(); // 隐藏当前的fragment，add下一个到Activity中
//            } else {
//                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
//            }
//        }
//    }

    //获取宿主Activity
    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (BaseActivity) activity;
    }

    //添加fragment
    protected void addFragment(BaseFragment fragment) {
        if (null != fragment) {
            getHoldingActivity().addFragment(fragment);
        }
    }

    //移除fragment
    protected void removeFragment() {
        getHoldingActivity().removeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG,"onCreateView");
//        View view = inflater.inflate(getLayoutId(), container, false);
//        initView(view, savedInstanceState);
//        return view;
        if(rootView == null){
            rootView = inflater.inflate(getLayoutId(), null);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        initView(rootView, savedInstanceState);
//        initToolbar(getHoldingActivity(),R.id.base_toolbar);
        isOnCreate = true;
//        setToolbar(rootView,"baseTitle");
        return rootView;
    }

    private void initToolbar(BaseActivity holdingActivity,int viewId) {
        mToolbar = (Toolbar) getActivity().findViewById(R.id.base_toolbar);
        getHoldingActivity().setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFragment();
            }
        });
        toolBarTitle = (TextView) getHoldingActivity().findViewById(R.id.toolbar_title);
        toolBarTitle.setText("hehe");
        getHoldingActivity().getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

//    protected abstract void initToolbar(BaseActivity holdingActivity,int viewId,View view);

    public void setToolbar(View view,String title){
//        mToolbar = (Toolbar) view.findViewById(R.id.base_toolbar);
        getHoldingActivity().setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFragment();
            }
        });
        toolBarTitle = (TextView) getHoldingActivity().findViewById(R.id.toolbar_title);
        toolBarTitle.setText(title);
        getHoldingActivity().getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void setNavigtion(int viewId,boolean visible){
        if(mToolbar!=null){
            if(visible){
                mToolbar.setNavigationIcon(viewId);
            }else{
                mToolbar.setNavigationIcon(null);
            }
        }
    }

}