package com.price.take_new.view.fragment.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.takeretrofit.bean.delivery.DeliveryDatum;
import com.example.takeretrofit.utils.ManagerData;
import com.price.take_new.BaseFragment;
import com.price.take_new.Constant;
import com.price.take_new.R;
import com.price.take_new.presenter.GetExpressPresenter;
import com.price.take_new.service.viewService.IGetExpressView;
import com.price.take_new.utils.adapter.RVPackageAdapter;
import com.price.take_new.utils.listener.LoadMoreListener;
import com.price.take_new.utils.listener.RVOnItemClickListener;
import com.price.take_new.view.activity.home.AuthoActivity;
import com.price.take_new.view.activity.home.ExpressDetailActivity;
import com.price.take_new.view.activity.home.HomeActivity;
import com.price.take_new.view.activity.home.PublishExpressActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


/**
 * 快递发布页面
 * Created by lenovo on 2016/7/19.
 */
public class Express_Plaza_Tab_Fragment extends BaseFragment
        implements SwipeRefreshLayout.OnRefreshListener, RVOnItemClickListener, IGetExpressView {

    private int isToken = 0;//记录已经被拿的快递的item项

    private String token;

    private String page = "0";

    private int pageCount = 0;
    private boolean isLoading;

    private int result;

    private RecyclerView rv_package_list;
    private SwipeRefreshLayout refresh;

    private List<DeliveryDatum> mData;
    private RVPackageAdapter mAdapter;

    private FloatingActionButton fab;
    private TextView loadMore;

    private GetExpressPresenter getExpressPresenter;

    private static int index = 0;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        getExpressPresenter = new GetExpressPresenter(this);
        Bundle bundle = getArguments();
        if(bundle!=null){
            token = bundle.getString(Constant.KEY_TOKEN);
        }
        initListView(view);
        initData();
        rv_package_list.setAdapter(mAdapter);
    }

    private static Express_Plaza_Tab_Fragment instance = new Express_Plaza_Tab_Fragment();

    public static Express_Plaza_Tab_Fragment newInstance(){
        if(instance == null){
            instance = new Express_Plaza_Tab_Fragment();
        }
        return instance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_express_plaza_tab;
    }

    private void initListView(View view){
        refresh = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        rv_package_list = (RecyclerView) view.findViewById(R.id.rv_package_list);
        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rv_package_list.setLayoutManager(manager);
        //设置增加或删除条目的动画
        rv_package_list.setItemAnimator(new DefaultItemAnimator());
        refresh.setOnRefreshListener(this);
        fab = (FloatingActionButton) view.findViewById(R.id.floatingactionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), PublishExpressActivity.class);
                    intent.putExtra(Constant.KEY_TOKEN, token);
                    startActivityForResult(intent,2);
                }
//                addFragment(PublishExpressActivity.newInstance());
        });
    }


    /*
    * initData方法将初始化mData和mAdapter
    * */
    private void initData() {
        mData = new ArrayList<>();
        mAdapter = new RVPackageAdapter(getActivity(),mData,R.layout.main_express_item,token);
        loadData(Constant.REFRESHMORE);
        Log.e("mData_init",mData.size()+"");
        mAdapter.setOnItemListener(this);
        mAdapter.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void setLoad(View view) {
                loadMore = (TextView) view;
            }
        });
        mAdapter.notifyDataSetChanged();
    }

    /*
    * @index 标识，index=LOADMORE则是点击加载更多，REFRESHMORE为下拉刷新
    * 联网完成数据的填充，后期将加入本地数据库存储功能
    * */
    private void loadData(int index) {
        pageCount = (index == Constant.REFRESHMORE ? 0:pageCount);
        this.index = index;
        Log.e("this.index",index+"  "+pageCount);
        page = pageCount+"";
        getExpressPresenter.getExpress(token,page,index);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case RESULT_OK:
                Log.e("expressTab","is ok?  "+isToken);
//            loadData(Constant.REFRESHMORE);
                mData.remove(isToken);
                mAdapter.notifyDataSetChanged();
                break;
            case 22:
                Log.e("expressTab","publish finish");
                loadData(Constant.REFRESHMORE);
                break;
        }
        if(resultCode == RESULT_OK){

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
            Toast.makeText(getActivity(),"load",Toast.LENGTH_LONG).show();
            return;
        }
        if(!ManagerData.getAuth(getActivity())){
            check();
            return;
        }
        if(position<mData.size()){
            Intent intent = new Intent(getActivity(), ExpressDetailActivity.class);
            DeliveryDatum data = mData.get(position);
            intent.putExtra(Constant.KEY_TOKEN,token);
            intent.putExtra(Constant.KEY_ORDER_ID,data.getId());//订单id
            intent.putExtra(Constant.KEY_POSTER_ID,mData.get(position).getUserId());//发布者id
            intent.putExtra(Constant.KEY_COMPANY,data.getCompany());
            intent.putExtra(Constant.KEY_TAKE_TIME,data.getTakeTime());
            intent.putExtra(Constant.KEY_PLACE,data.getPlace());
            intent.putExtra(Constant.KEY_ADDRESS,data.getAddress());
            intent.putExtra(Constant.KEY_DESCRIPTION,data.getDes());
            intent.putExtra(Constant.KEY_PRICE,data.getPrice());
            isToken = position;
            Log.e("istoken",position+"");
            startActivityForResult(intent,1);
//            startActivity(intent);
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
    public void showToast(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
        isLoading = false;
    }

    @Override
    public void showLoading(int index) {
        switch (index){
            case Constant.LOADMORE:
                loadMore.setVisibility(View.GONE);
                break;
            case Constant.REFRESHMORE:
                refresh.setRefreshing(true);
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
                refresh.setRefreshing(false);
                break;
        }
    }

    @Override
    public void showData(List<DeliveryDatum> delivery) {
//        loadMore.setVisibility(View.VISIBLE);
        isLoading = false;
        mAdapter.clear();
        Log.e("index",index+"");
        if(index == Constant.REFRESHMORE){
            mData.addAll(0,delivery);
        } else{
            mData.addAll(delivery);
            pageCount+=1;
        }
        Log.e("size",""+mData.size());
        mAdapter.notifyDataSetChanged();
    }

    private void check(){
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setMessage("您尚未进行个人真实资料认证，认证后方能正常使用，是否现在认证？")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Intent intent = new Intent(getActivity(),AuthoActivity.class);
                        intent.putExtra(Constant.KEY_TOKEN,token);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }
}
