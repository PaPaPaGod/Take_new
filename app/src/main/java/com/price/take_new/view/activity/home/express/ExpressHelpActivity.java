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

import java.util.ArrayList;
import java.util.List;


/**
 * 代拿快递页面
 * Created by lenovo on 2016/7/31.
 */
public class ExpressHelpActivity extends HomeBaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, RVOnItemClickListener, IMyExpressView {

    private TextView loadMore;

    private List<MyHelpOrderDatum> mData;
    private PackageHelpAdapter mAdapter;

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView rv_list;

    private String token;
    private String page = "0";

    private int pageCount = 0;

    private boolean isLoading;

    private static int index;

    private MyExpressPresenter presenter;


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

    private void loadData(int index) {
        pageCount = (index == Constant.REFRESHMORE ? 0:pageCount);
        this.index = index;
        Log.e("this.index",index+"  "+pageCount);
        page = pageCount+"";
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
        rv_list.setItemAnimator( new DefaultItemAnimator());
        refreshLayout.setOnRefreshListener(this);
    }

    private void initDatas() {
        mData = new ArrayList<>();
        mAdapter = new PackageHelpAdapter(this,mData,R.layout.item_help_express,token);
        loadData(Constant.REFRESHMORE);
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
            loadData(Constant.REFRESHMORE);
        }
    }



    @Override
    public void onItemClick(View view, int position) {
        Log.e("click",position+"   ");
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
