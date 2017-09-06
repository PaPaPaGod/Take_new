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
import com.example.takeretrofit.bean.otheruserinfo.OtherUserInfoData;
import com.price.take_new.Constant;
import com.price.take_new.HomeBaseActivity;
import com.price.take_new.R;
import com.price.take_new.bean.UserInfo;
import com.price.take_new.presenter.GetOtherUserInfoPresenter;
import com.price.take_new.presenter.GetUserInfoPresenter;
import com.price.take_new.presenter.MyExpressPresenter;
import com.price.take_new.service.viewService.IGetOtherUserInfoView;
import com.price.take_new.service.viewService.IGetUserInfoView;
import com.price.take_new.service.viewService.IMyExpressView;
import com.price.take_new.utils.adapter.PackageHelpAdapter;
import com.price.take_new.utils.listener.LoadMoreListener;
import com.price.take_new.utils.listener.RVOnItemClickListener;
import com.price.take_new.utils.listener.ScrollRecyclerViewListener;

import java.util.ArrayList;
import java.util.List;


/**
 * 代拿快递页面
 * Created by lenovo on 2016/7/31.
 */
public class ExpressHelpActivity extends HomeBaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, RVOnItemClickListener, IMyExpressView {

    private static final String TAG = "ExpressHelpActivity";
    private TextView loadMore;

    private List<MyHelpOrderDatum> mData;
    private PackageHelpAdapter mAdapter;

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView rv_list;

    private String token;
    private String page = "0";

    private int pageCount = 0;

    private boolean isLoading;
    private boolean isEnd;

    private static int index;

    private MyExpressPresenter presenter;
    private ScrollRecyclerViewListener mScrollListener;
    private int currentPage=0;


    @Override
    public void initView() {
        presenter = new MyExpressPresenter(this);
        initListView();
        initDatas();
        rv_list.setAdapter(mAdapter);
    }

    @Override
    public int getContentViewId() {
        return R.layout.express_help;
    }

    @Override
    public String getToolBarTitle() {
        return "代拿的快递";
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
        if(intent !=null){
            token = intent.getStringExtra(Constant.KEY_TOKEN);
        }
    }

    private void loadData(int index,int currentPage) {
        this.currentPage = currentPage;
        this.index = index;
        Log.e(TAG,"currentPage::"+currentPage+","+"pageCount::"+pageCount+","+"page::"+page);
        page = currentPage+"";
        presenter.getHelpDelivery(token,page,index);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
//            case R.id.person_express_portrait:
//                Intent intent = new Intent(this,PersonInfoActivity.class);
//                intent.putExtra(Config.USER_ID,"");
//                break;
            default:
                finish();
        }

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
    }

    private void initDatas() {
        mData = new ArrayList<>();
        mAdapter = new PackageHelpAdapter(this,mData,R.layout.item_help_express,token);
        loadData(Constant.REFRESHMORE,0);
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
    public void onRefresh() {
        if(!isLoading){
            isLoading = true;
            loadData(Constant.REFRESHMORE,0);
        }
    }



    @Override
    public void onItemClick(View view, int position) {
        if(position == mData.size()){
            return;
        }
        if(position<mData.size()){
            Intent intent = new Intent(this,ExpressHelpDetailActivity.class);
            int pos = position;
            intent.putExtra(Constant.KEY_TOKEN,token);
            intent.putExtra(Constant.KEY_NAME,mData.get(pos).getName());
            intent.putExtra(Constant.KEY_POSTER_ID,mData.get(pos).getUserId());
            intent.putExtra(Constant.KEY_STATUS,mData.get(pos).getStatus());
            intent.putExtra(Constant.KEY_COMPANY,mData.get(pos).getCompany());
            intent.putExtra(Constant.KEY_PLACE,mData.get(pos).getPlace());
            intent.putExtra(Constant.KEY_ADDRESS,mData.get(pos).getAddress());
            intent.putExtra(Constant.KEY_TAKE_TIME,mData.get(pos).getTakeTime());
            intent.putExtra(Constant.KEY_SMS,mData.get(pos).getSms_content());
            Log.e("help_express",mData.get(pos).getCompany());
            startActivity(intent);
        }
    }

    @Override
    public void showHelpData(List<MyHelpOrderDatum> data) {
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
        for(MyHelpOrderDatum datum:data){
            Log.e(TAG,datum.getCreated());
        }
    }

    @Override
    public void showMyData(List<MyDeliveryOrderDatum> mData) {

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
