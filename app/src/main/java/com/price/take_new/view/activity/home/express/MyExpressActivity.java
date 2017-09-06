package com.price.take_new.view.activity.home.express;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.takeretrofit.bean.delivery.DeliveryDatum;
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
import com.price.take_new.utils.listener.ScrollRecyclerViewListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


/**
 * 我的快递页面
 * Created by lenovo on 2016/8/1.
 */
public class MyExpressActivity extends HomeBaseActivity implements SwipeRefreshLayout.OnRefreshListener, RVOnItemClickListener, IMyExpressView {

    private static final String TAG = "myExpressActivity";
    private TextView loadMore;

    private List<MyDeliveryOrderDatum> mData;
    private PackageMyAdapter mAdapter;

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView rv_list;

    private MyExpressPresenter presenter;

    private String token;
    private String page = "0";

    private int pageCount = 0;
    private int currentPage = 0;

    private boolean isLoading;
    private boolean isEnd;

    private int isSelected;

    private static int index;
    private ScrollRecyclerViewListener mScrollListener;

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

    private void loadData(int index,int currentPage) {
        this.index = index;
        Log.e(TAG,"currentPage::"+currentPage+","+"pageCount::"+pageCount+","+"page::"+page);
        page = currentPage+"";
        presenter.getMyDelivery(token,page,index);
    }

    private void initListView() {
        rv_list = (RecyclerView) findViewById(R.id.express_help_recyclerview);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.express_help_refresh);
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        rv_list.setLayoutManager(manager);
        mScrollListener = new ScrollRecyclerViewListener(manager) {
            @Override
            public void onLoadMore(int currentPage) {
                if (!isLoading && !isEnd) {
//                    Toast.makeText(getActivity(),"正在加载...",Toast.LENGTH_LONG).show();
                    isLoading = true;
//                    loadMore.setVisibility(View.INVISIBLE);
                    loadData(Constant.LOADMORE, currentPage);
                }
            }
        };
        rv_list.addOnScrollListener(mScrollListener);
        rv_list.setItemAnimator( new DefaultItemAnimator());
        refreshLayout.setOnRefreshListener(this);
        presenter = new MyExpressPresenter(this);
    }

    private void initDatas() {
        mData = new ArrayList<>();
        loadData(Constant.REFRESHMORE,0);
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
                loadData(Constant.REFRESHMORE,0);
                break;
        }
    }


    @Override
    public void onRefresh() {
        if(!isLoading){
            isLoading = true;
            loadData(Constant.REFRESHMORE,0);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this,MyExpressDetailActivity.class);
        int pos = position;
        isSelected = pos;
        if(position == mData.size()){
            return;
        }
        if(position<mData.size()){
            MyDeliveryOrderDatum data = mData.get(pos);
            intent.putExtra(Constant.KEY_TOKEN,token);
            intent.putExtra(Constant.KEY_ORDER_ID,mData.get(pos).getId());
            intent.putExtra(Constant.KEY_COMPANY,mData.get(pos).getCompany());
            intent.putExtra(Constant.KEY_PLACE,mData.get(pos).getPlace());
            intent.putExtra(Constant.KEY_ADDRESS,mData.get(pos).getAddress());
            intent.putExtra(Constant.KEY_SMS,mData.get(pos).getSms_content());
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
                intent.putExtra(Constant.KEY_ACCEPTER_ID,accepter_id);
                if(TextUtils.isEmpty(data.getAccepterName())){
                    Log.e(TAG,"accetername is null");
                }else{
                    Log.e(TAG,data.getAccepterName());

                }
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
        Log.e(TAG,"page::"+page);
        isLoading = false;
        if(index == Constant.REFRESHMORE){
            mAdapter.clear();
            Log.e(TAG,"add to head");
            mData.clear();
            mData.addAll(data);
            mScrollListener.setCurrentPage(0);
            isEnd = false;
            Log.e(TAG,"下拉刷新完成");
        } else{
            Log.e(TAG,"add to end");
            mData.addAll(data);
            mScrollListener.setloadFinish(true);
            Log.e(TAG,"上拉加载完成,page"+currentPage);
        }
        mAdapter.notifyDataSetChanged();
        Log.e(TAG,"currentItem :"+mAdapter.getItemCount());
        Log.e(TAG,"page:"+page+",size:"+data.size());
        for(MyDeliveryOrderDatum datum:data){
            Log.e(TAG,datum.getCreated());
        }
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
