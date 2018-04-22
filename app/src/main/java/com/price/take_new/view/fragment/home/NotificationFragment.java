package com.price.take_new.view.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.takeretrofit.bean.sysnotification.NotificationDatum;
import com.price.take_new.BaseFragment;
import com.price.take_new.R;
import com.price.take_new.utils.adapter.NotificationAdapter;
import com.price.take_new.utils.adapter.PackageHelpAdapter;
import com.price.take_new.utils.listener.LoadMoreListener;
import com.price.take_new.utils.listener.RVOnItemClickListener;
import com.price.take_new.utils.listener.ScrollRecyclerViewListener;
import com.price.take_new.view.activity.PackageDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by intel on 2/18/2018.
 */

public class NotificationFragment extends BaseFragment implements RVOnItemClickListener {
    private static final String TAG = "NotificationFragment";

    private RecyclerView mRecyclerView;
    private List<NotificationDatum> mData;
    private NotificationAdapter mAdapter;

    private ScrollRecyclerViewListener mScrollListener;

//    private static NotificationFragment instance = new NotificationFragment();
//    public static NotificationFragment newInstance(){
//        if(instance == null){
//            instance = new NotificationFragment();
//        }
//        return instance;
//    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        Log.e(TAG,"NotificationFragment initView");
        initToolbar(view);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.notification_rv);
        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        //设置增加或删除条目的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mScrollListener = new ScrollRecyclerViewListener(manager) {
            @Override
            public void onLoadMore(int currentPage) {

            }
        };
        mRecyclerView.addOnScrollListener(mScrollListener);
        initData();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initToolbar(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.notification_toolbar);
        getHoldingActivity().setSupportActionBar(mToolbar);

        toolBarTitle = (TextView) view.findViewById(R.id.toolbar_title);
        toolBarTitle.setText("通知列表");
        getHoldingActivity().getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setNavigationIcon(R.drawable.icon_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFragment();
            }
        });
    }

    private void initData() {
        mData = new ArrayList<>();
        NotificationDatum datum = new NotificationDatum();
        NotificationDatum datum1 = new NotificationDatum();
        datum1.setStyle(0);
        datum.setStyle(1);
        mData.add(datum);
        mData.add(datum1);
        mAdapter = new NotificationAdapter(getActivity(),mData,R.layout.item_notification,null);
        mAdapter.setOnItemListener(this);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notification;
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.e(TAG,"onItemClick");
        Intent intent = new Intent(getActivity(), PackageDetailActivity.class);
        startActivityForResult(intent,1);
        if(position<mData.size()){

        }
    }


}
