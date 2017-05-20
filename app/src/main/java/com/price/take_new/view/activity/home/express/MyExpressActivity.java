package com.price.take_new.view.activity.home.express;

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
import android.widget.Toast;

import com.example.takeretrofit.bean.mydeliveryorder.MyDeliveryOrderDatum;
import com.example.takeretrofit.bean.myhelpdeliveryorder.MyHelpOrderDatum;
import com.price.take_new.Constant;
import com.price.take_new.HomeBaseActivity;
import com.price.take_new.R;
import com.price.take_new.presenter.MyExpressPresenter;
import com.price.take_new.service.viewService.IMyExpressView;
import com.price.take_new.utils.adapter.PackageHelpAdapter;
import com.price.take_new.utils.adapter.PackageMyAdapter;
import com.price.take_new.utils.item.PersonalExpressItem;
import com.price.take_new.utils.listener.LoadMoreListener;
import com.price.take_new.utils.listener.RVOnItemClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * 我的快递页面
 * Created by lenovo on 2016/8/1.
 */
public class MyExpressActivity extends HomeBaseActivity implements SwipeRefreshLayout.OnRefreshListener, RVOnItemClickListener, IMyExpressView {

    private TextView loadMore;

    private List<MyDeliveryOrderDatum> mData;
    private PackageMyAdapter mAdapter;

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView rv_list;

    private MyExpressPresenter presenter;

    private String token;
    private String page = "0";

    private int pageCount = 0;

    private boolean isLoading;

    private int isSelected;

    private static int index;

    @Override
    public void initView() {
        initListView();
        initDatas();
        rv_list.setAdapter(mAdapter);
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
        if(intent!=null){
            token = intent.getStringExtra(Constant.KEY_TOKEN);
        }
    }

    @Override
    public String getToolBarTitle() {
        return "我的快递";
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_my_express;
    }

    private void loadData(int index) {
        pageCount = (index == Constant.REFRESHMORE ? 0:pageCount);
        this.index = index;
        Log.e("this.index",index+"  "+pageCount);
        page = pageCount+"";
        presenter.getMyDelivery(token,page,index);
    }

    private void initListView() {
        rv_list = (RecyclerView) findViewById(R.id.express_help_recyclerview);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.express_help_refresh);
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        rv_list.setLayoutManager(manager);
        rv_list.setItemAnimator( new DefaultItemAnimator());
        refreshLayout.setOnRefreshListener(this);
        presenter = new MyExpressPresenter(this);
    }

    private void initDatas() {
        mData = new ArrayList<>();
        loadData(Constant.REFRESHMORE);
        mAdapter = new PackageMyAdapter(this,mData,R.layout.item_help_express,token);
        mAdapter.setOnItemListener(this);
        mAdapter.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void setLoad(View view) {
                loadMore = (TextView) view;
            }
        });
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case Constant.SUCCESS_REFRESH:
                loadData(Constant.REFRESHMORE);
                break;
        }
    }


    @Override
    public void onRefresh() {
        if(!isLoading){
            isLoading = true;
            loadData(Constant.REFRESHMORE);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this,MyExpressDetailActivity.class);
        int pos = position;
        Log.e("myexpress_pos",pos+"");
        isSelected = pos;
        if(position == mData.size()){
            if(!isLoading){
                isLoading = true;
                loadMore.setVisibility(View.GONE);
                loadData(Constant.LOADMORE);
            }
            Toast.makeText(this,"load",Toast.LENGTH_LONG).show();
            return;
        }
        if(position<mData.size()){
            intent.putExtra(Constant.KEY_TOKEN,token);
            intent.putExtra(Constant.KEY_ORDER_ID,mData.get(pos).getId());
            intent.putExtra(Constant.KEY_COMPANY,mData.get(pos).getCompany());
            intent.putExtra(Constant.KEY_PLACE,mData.get(pos).getPlace());
            intent.putExtra(Constant.KEY_ADDRESS,mData.get(pos).getAddress());
            intent.putExtra(Constant.KEY_SMS,mData.get(pos).getSms_content());
            Log.e("address",mData.get(pos).getAddress()==null?"yes":"no");
            intent.putExtra(Constant.KEY_TAKE_TIME,mData.get(pos).getTakeTime());
            String status = mData.get(pos).getStatus();
            if(status==null)
                Log.e("status","status is null");
            else
                Log.e("status",status);
            intent.putExtra(Constant.KEY_STATUS,status);
            if(status.equals("2")){
                String accepter_id = "";
                accepter_id = mData.get(pos).getAccepterId();
                Log.e("accepter_id",accepter_id);
                intent.putExtra(Constant.KEY_ACCEPTER_ID,accepter_id);
                intent.putExtra(Constant.KEY_ACCEPTER_NAME,mData.get(pos).getAccepterName());
            }
            startActivity(intent);
        }

    }

    @Override
    public void showHelpData(List<MyHelpOrderDatum> mData) {

    }

    @Override
    public void showMyData(List<MyDeliveryOrderDatum> data) {
        isLoading = false;
        mAdapter.clear();
        Log.e("index",index+"");
        if(index == Constant.REFRESHMORE){
            mData.addAll(0,data);
        } else{
            mData.addAll(data);
            pageCount+=1;
        }
        Log.e("size",""+mData.size());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String msg, int code) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        isLoading = false;
    }

    @Override
    public void showLoading(int index) {
        switch (index){
            case Constant.LOADMORE:
                loadMore.setVisibility(View.GONE);
                break;
            case Constant.REFRESHMORE:
                refreshLayout.setRefreshing(true);
                break;
        }
    }

    @Override
    public void hideLoading(int index) {
        switch (index){
            case Constant.LOADMORE:
                loadMore.setVisibility(View.VISIBLE);
                break;
            case Constant.REFRESHMORE:
                refreshLayout.setRefreshing(false);
                break;
        }
    }
}
