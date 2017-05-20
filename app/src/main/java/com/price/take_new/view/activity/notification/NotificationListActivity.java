package com.price.take_new.view.activity.notification;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.takeretrofit.bean.sysnotification.NotificationDatum;
import com.price.take_new.Constant;
import com.price.take_new.HomeBaseActivity;
import com.price.take_new.R;
import com.price.take_new.presenter.SysNotificationPresenter;
import com.price.take_new.service.viewService.ISysNotificationView;
import com.price.take_new.utils.adapter.NotificationAdapter;
import com.price.take_new.utils.listener.RVOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by price on 3/14/2017.
 */

public class NotificationListActivity extends HomeBaseActivity implements RVOnItemClickListener, ISysNotificationView {
    private RecyclerView sys_list;
    private NotificationAdapter mAdapter;

    private String token;
    private String page = "0";
    private String total = "0";
    private List<NotificationDatum> mData;

    private TextView tv_tips;


    private SysNotificationPresenter presenter;

    @Override
    public void initView() {
        tv_tips = (TextView) findViewById(R.id.tv_tips);
        initListView();
        initDatas();
        sys_list.setAdapter(mAdapter);
    }

    private void initDatas() {
        presenter = new SysNotificationPresenter(this);
        mData = new ArrayList<>();
        mAdapter = new NotificationAdapter(this,mData,R.layout.item_sys_notification,token);
        mAdapter.setOnItemListener(this);
        mAdapter.notifyDataSetChanged();
        if(token!=null){
            presenter.sysNotification(token,"0","99");
        }
    }

    @Override
    public String getToolBarTitle() {
        return "消息";
    }

    private void initListView() {
        sys_list = (RecyclerView) findViewById(R.id.sys_notification_list);
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        sys_list.setLayoutManager(manager);
        sys_list.setItemAnimator( new DefaultItemAnimator());
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
        if(intent !=null){
            token = intent.getStringExtra(Constant.KEY_TOKEN);
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_notificationlist;
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this,SysNotificationDetailActivity.class);
        NotificationDatum datum = mData.get(position);
        intent.putExtra(Constant.KEY_TOKEN,token);
        intent.putExtra(Constant.KEY_NAME,datum.getOther_name());
        intent.putExtra(Constant.KEY_ORDER_ID,datum.getOrder_id());
        intent.putExtra(Constant.USER_ID,datum.getOther_id());
        intent.putExtra(Constant.CREATED_TIME,datum.getCreated());
        startActivity(intent);
    }

    @Override
    public void showData(List<NotificationDatum> data) {
        if (mData.size()!=0){
            mData.clear();
        }
        List<NotificationDatum> m = new ArrayList<>();
        for(NotificationDatum datum:data){
            if(datum.getIs_taker().equals("1")){
                m.add(datum);
            }
        }
        if(m.size() == 0){
            tv_tips.setVisibility(View.VISIBLE);
        }else {
            tv_tips.setVisibility(View.GONE);
        }
        mData.addAll(m);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String msg, int code) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
