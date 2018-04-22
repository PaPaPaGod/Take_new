package com.price.take_new.view.fragment.pager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.takeretrofit.bean.mydeliveryorder.MyDeliveryOrderDatum;
import com.example.takeretrofit.bean.myhelpdeliveryorder.MyHelpOrderDatum;
import com.example.takeretrofit.utils.ManagerData;
import com.price.take_new.BaseFragment;
import com.price.take_new.Constant;
import com.price.take_new.R;
import com.price.take_new.presenter.MyExpressPresenter;
import com.price.take_new.service.viewService.IMyExpressView;
import com.price.take_new.utils.adapter.PackageHelpAdapter;
import com.price.take_new.utils.listener.LoadMoreListener;
import com.price.take_new.utils.listener.RVOnItemClickListener;
import com.price.take_new.utils.listener.ScrollRecyclerViewListener;
import com.price.take_new.view.activity.PackageDetailActivity;
import com.price.take_new.view.activity.PersonalMsgActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by intel on 2/18/2018.
 */

public class MyHelpPackagePager extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, RVOnItemClickListener, IMyExpressView {
    private static final String TAG = "MyHelpPackagePager";

    private RecyclerView mRecyclerView;
    private List<MyHelpOrderDatum> mData;
    private PackageHelpAdapter mAdapter;

    private SwipeRefreshLayout mRefreshLayout;

    private TextView loadMore;
    private TextView end;

    private String token;

    private String page = "0";
    private int isToken = 0;//记录已经被拿的快递的item项
    private int pageCount = 0;
    private int currentPage = 0;
    private static int index;

    private boolean isEnd = false;

    private boolean isLoading;

    private ScrollRecyclerViewListener mScrollListener;

    private MyExpressPresenter presenter;

//    private static MyHelpPackagePager instance = new MyHelpPackagePager();
//    public static MyHelpPackagePager newInstance(){
//        if(instance == null){
//            instance = new MyHelpPackagePager();
//        }
//        return instance;
//    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        presenter = new MyExpressPresenter(this);
        token = ManagerData.getCachedToken(getActivity());
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_myhelp_package);
//        loadMore
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_myhelp_package);
        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        //设置增加或删除条目的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRefreshLayout.setOnRefreshListener(this);
        mScrollListener = new ScrollRecyclerViewListener(manager) {
            @Override
            public void onLoadMore(int currentPage) {
                if(!isLoading && !isEnd){
//                    Toast.makeText(getActivity(),"正在加载...",Toast.LENGTH_LONG).show();
                    isLoading = true;
//                    loadMore.setVisibility(View.INVISIBLE);
                    loadData(Constant.LOADMORE,currentPage);
                }
            }
        };
        mRecyclerView.addOnScrollListener(mScrollListener);
        initData();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        mData = new ArrayList<>();
        loadData(Constant.REFRESHMORE,0);
        mAdapter = new PackageHelpAdapter(getActivity(),mData,R.layout.item_personal_express,null);
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
    protected int getLayoutId() {
        return R.layout.pager_myhelp_package;
    }

    @Override
    public void onItemClick(View view, int position) {
        if(position<mData.size()){
            Intent intent = new Intent(getActivity(), PersonalMsgActivity.class);
            intent.putExtra(Constant.FRAGMENT_NAME,Constant.HELP_PACKAGE_DETAIL);
            Bundle bundle = new Bundle();
            MyHelpOrderDatum data = mData.get(position);
            bundle.putString(Constant.KEY_DES,data.getDes());
            bundle.putString(Constant.KEY_COMPANY,data.getCompany());
            bundle.putString(Constant.KEY_PRICE,data.getPrice());
            bundle.putString(Constant.KEY_SMALL_REWARD,data.getSmall_reward());
            bundle.putString(Constant.KEY_ADDRESS,data.getAddress());
            bundle.putInt(Constant.AT_SCHOOL,data.getAt_school());
            bundle.putString(Constant.TB_NAME,data.getNickname());
            bundle.putString(Constant.KEY_NAME,data.getName());
            bundle.putString(Constant.KEY_ID,data.getId());
            bundle.putString(Constant.KEY_USER_ID,data.getUserId());
            bundle.putString(Constant.KEY_SMS,data.getSms_content());
            intent.putExtras(bundle);
            startActivity(intent);
            isToken = position;
        }
    }

    @Override
    public void onRefresh() {
        if(!isLoading){
            isLoading = true;
            loadData(Constant.REFRESHMORE,0);
        }
    }

    private void loadData(int index,int currentPage) {
        this.index = index;
        Log.e(TAG,"currentPage::"+currentPage+","+"pageCount::"+pageCount+","+"page::"+page);
        page = currentPage+"";
        presenter.getHelpDelivery(token,page,index);
    }

    @Override
    public void showHelpData(List<MyHelpOrderDatum> mData) {
        Log.e(TAG,"size:"+mData.size());
        isLoading = false;
        if(index == Constant.REFRESHMORE){
            mAdapter.clear();
            Log.e(TAG,"add to head");
            this.mData.clear();
            this.mData.addAll(mData);
            mScrollListener.setCurrentPage(0);
            isEnd = false;
            Log.e(TAG,"下拉刷新完成");
        } else{
            Log.e(TAG,"add to end");
            mData.addAll(mData);
            mScrollListener.setloadFinish(true);
            Log.e(TAG,"上拉加载完成,page"+currentPage);
        }
        mAdapter.notifyDataSetChanged();
        Log.e(TAG,"currentItem :"+mAdapter.getItemCount());
        Log.e(TAG,"page:"+page+",size:"+mData.size());
        for(MyHelpOrderDatum datum:mData){
            Log.e(TAG,datum.getCreated());
        }
    }

    @Override
    public void showMyData(List<MyDeliveryOrderDatum> mData) {

    }

    @Override
    public void showToast(String msg, int code) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
        isLoading = false;
    }

    @Override
    public void showLoading(int index) {
        switch (index) {
            case Constant.LOADMORE:
                loadMore.setVisibility(View.GONE);
                break;
            case Constant.REFRESHMORE:
                mRefreshLayout.setRefreshing(true);
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
                mRefreshLayout.setRefreshing(false);
                break;
        }
    }
}
