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
import com.price.take_new.utils.adapter.PackageMyAdapter;
import com.price.take_new.utils.adapter.RVPackageAdapter;
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

public class MyPackagePager extends BaseFragment implements RVOnItemClickListener, IMyExpressView {
    private static final String TAG = "MyPackagePager";

    private RecyclerView mRecyclerView;
    private List<MyDeliveryOrderDatum> mData;
    private PackageMyAdapter mAdapter;

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

//    private static MyPackagePager instance = new MyPackagePager();
//    public static MyPackagePager newInstance(){
//        if(instance == null){
//            instance = new MyPackagePager();
//        }
//        return instance;
//    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        presenter = new MyExpressPresenter(this);
        token = ManagerData.getCachedToken(getActivity());
        Log.e(TAG,"MainPackageFragment initView");
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_my_package);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_my_package);
        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mScrollListener = new ScrollRecyclerViewListener(manager) {
            @Override
            public void onLoadMore(int currentPage) {
                if(!isLoading && !isEnd){
                    isLoading = true;
                    loadData(Constant.LOADMORE,currentPage);
                }
            }
        };
        mRecyclerView.addOnScrollListener(mScrollListener);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(!isLoading){
                    isLoading = true;
                    loadData(Constant.REFRESHMORE,0);
                }
            }
        });
        initData();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        mData = new ArrayList<>();
        loadData(Constant.REFRESHMORE,0);
        mAdapter = new PackageMyAdapter(getActivity(),mData,R.layout.item_personal_express,token);
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
        return R.layout.pager_my_package;
    }

    @Override
    public void onItemClick(View view, int position) {
        if(position<mData.size()){
            MyDeliveryOrderDatum data = mData.get(position);
            Intent intent = new Intent(getActivity(), PersonalMsgActivity.class);
            intent.putExtra(Constant.FRAGMENT_NAME,Constant.MY_PACKAGE_DETAIL);
            Bundle bundle = new Bundle();
            bundle.putString(Constant.KEY_ORDER_ID,data.getId());
            bundle.putString(Constant.KEY_USER_ID,data.getId());
            bundle.putString(Constant.KEY_ACCEPTER_ID,data.getAccepterId());
            bundle.putString(Constant.KEY_ACCEPTER_NAME,data.getAccepterName());
            bundle.putString(Constant.KEY_COMPANY,data.getCompany());
            bundle.putString(Constant.KEY_DES,data.getDes());
            bundle.putString(Constant.KEY_ADDRESS,data.getAddress());
            bundle.putString(Constant.KEY_SMS,data.getSms_content());
            bundle.putString(Constant.KEY_PRICE,data.getPrice());
            bundle.putString(Constant.KEY_SMALL_REWARD,data.getSmall_reward());
            intent.putExtras(bundle);
            startActivity(intent);
//            DeliveryDatum data = mData.get(position);
//            intent.putExtra(Constant.KEY_TOKEN,token);
//            intent.putExtra(Constant.KEY_ORDER_ID,data.getId());//订单id
//            intent.putExtra(Constant.KEY_POSTER_ID,mData.get(position).getUserId());//发布者id
//            intent.putExtra(Constant.KEY_COMPANY,data.getCompany());
//            intent.putExtra(Constant.KEY_TAKE_TIME,data.getTakeTime());
//            intent.putExtra(Constant.KEY_PLACE,data.getPlace());
//            intent.putExtra(Constant.KEY_ADDRESS,data.getAddress());
//            intent.putExtra(Constant.KEY_DESCRIPTION,data.getDes());
//            intent.putExtra(Constant.KEY_PRICE,data.getPrice());
            isToken = position;
//            Log.e(TAG,"position :"+position);
//            Log.e(TAG,"price :"+data.getPrice());
//            startActivity(intent);
        }
    }

    private void loadData(int index,int currentPage) {
        this.index = index;
        Log.e(TAG,"currentPage::"+currentPage+","+"pageCount::"+pageCount+","+"page::"+page);
        page = currentPage+"";
        presenter.getMyDelivery(token,page,index);
    }

    @Override
    public void showHelpData(List<MyHelpOrderDatum> mData) {

    }

    @Override
    public void showMyData(List<MyDeliveryOrderDatum> mData) {
        Log.e(TAG,"size"+mData.size());
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
        for(MyDeliveryOrderDatum datum:mData){
            Log.e(TAG,datum.getCreated());
        }
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
