package com.price.take_new.view.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.takeretrofit.bean.delivery.DeliveryDatum;
import com.example.takeretrofit.utils.ManagerData;
import com.price.take_new.BaseActivity;
import com.price.take_new.BaseFragment;
import com.price.take_new.Constant;
import com.price.take_new.R;
import com.price.take_new.presenter.GetExpressPresenter;
import com.price.take_new.service.viewService.IGetExpressView;
import com.price.take_new.utils.adapter.RVPackageAdapter;
import com.price.take_new.utils.listener.LoadMoreListener;
import com.price.take_new.utils.listener.RVOnItemClickListener;
import com.price.take_new.utils.listener.ScrollRecyclerViewListener;
import com.price.take_new.view.activity.MainTabActivity;
import com.price.take_new.view.activity.PublishActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;

/**
 * Created by intel on 2/18/2018.
 */

public class MainPackageFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, RVOnItemClickListener, IGetExpressView {
    private static final String TAG = "MainPackageFragment";

    private RecyclerView mRecyclerView;
    private ImageButton fab_publish;
    private List<DeliveryDatum> mData;
    private RVPackageAdapter mAdapter;

    private SwipeRefreshLayout mRefreshLayout;

    private Toolbar mToolbar;

    private TextView loadMore;
    private TextView end;

    private String token;

    private String page = "0";
    private int isToken = 0;//记录已经被拿的快递的item项
    private int pageCount = 0;
    private int currentPage = 0;
    private static int index = 0;

    private boolean isEnd = false;

    private boolean isLoading;

    private ScrollRecyclerViewListener mScrollListener;


    private GetExpressPresenter getExpressPresenter;

    private WeakReference<BaseFragment> reference;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mToolbar = (Toolbar) view.findViewById(R.id.main_package_toolbar);
        getHoldingActivity().setSupportActionBar(mToolbar);

        toolBarTitle = (TextView) view.findViewById(R.id.toolbar_title);
        toolBarTitle.setText("快递广场");
        getHoldingActivity().getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setNavigationIcon(null);
        mRightImage = (ImageView) view.findViewById(R.id.toolbar_right_icon);
        mRightImage.setImageResource(R.drawable.icon_notification_red);
        mRightImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MainTabActivity.class);
                intent.putExtra(Constant.FRAGMENT_NAME,Constant.MAIN_NOTIFICATION);
                startActivity(intent);
            }
        });

        getExpressPresenter = new GetExpressPresenter(this);
        token = ManagerData.getCachedToken(getActivity());
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.main_package_refresh);
//        loadMore
        mRecyclerView = (RecyclerView) view.findViewById(R.id.main_package_rv);
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
                    loadData(Constant.LOADMORE,currentPage,Constant.GETDELIVERY_DEFAULT);
                }
            }
        };
        mRecyclerView.addOnScrollListener(mScrollListener);
        initData();
        mRecyclerView.setAdapter(mAdapter);

        fab_publish = (ImageButton) view.findViewById(R.id.fab_publish);
        fab_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MainTabActivity.class);
                intent.putExtra(Constant.FRAGMENT_NAME,Constant.MAIN_PACKAGE_PUBLISH);
                startActivity(intent);
            }
        });

    }

    private void initData() {
        mData = new ArrayList<>();
        mAdapter = new RVPackageAdapter<>(getActivity(),mData,R.layout.item_main_express,null);
        loadData(Constant.REFRESHMORE,0,Constant.GETDELIVERY_DEFAULT);
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
        return R.layout.fragment_package;
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.e(TAG,"onItemClick");
        if(position == mData.size()){
//            if(!isLoading){
//                isLoading = true;
//                loadMore.setVisibility(View.GONE);
//                loadData(Constant.LOADMORE,currentPage);
//            }
//            Toast.makeText(getActivity(),"正在加载...",Toast.LENGTH_LONG).show();
            return;
        }
        if(position<mData.size()) {
            Intent intent = new Intent(getActivity(), MainTabActivity.class);
            intent.putExtra(Constant.FRAGMENT_NAME,Constant.MAIN_PACKAGE_DETAIL);
            DeliveryDatum data = mData.get(position);
            // TODO: 3/8/2018 将内容序列化，将整个datum发过去
            Bundle bundle = new Bundle();
            bundle.putString(Constant.KEY_TOKEN, token);
            bundle.putString(Constant.KEY_ORDER_ID, data.getId());//订单id
            bundle.putString(Constant.KEY_POSTER_ID, mData.get(position).getUserId());//发布者id
            bundle.putString(Constant.KEY_COMPANY, data.getCompany());
            bundle.putString(Constant.KEY_PLACE, data.getPlace());
            bundle.putString(Constant.KEY_ADDRESS, data.getAddress());
            bundle.putString(Constant.KEY_DESCRIPTION, data.getDes());
            bundle.putString(Constant.KEY_PRICE, data.getPrice());
            bundle.putString(Constant.KEY_SMALL_REWARD, data.getSmall_reward());
            intent.putExtras(bundle);
            isToken = position;
            Log.e(TAG, "position :" + position);
            Log.e(TAG, "price :" + data.getPrice());
            startActivityForResult(intent, 1);
//            startActivity(intent);
        }
    }

    @Override
    public void onRefresh() {
        if(!isLoading){
            isLoading = true;
            loadData(Constant.REFRESHMORE,0,Constant.GETDELIVERY_DEFAULT);
        }
    }

    private void loadData(int index,int currentPage,String filter) {
        this.currentPage = currentPage;
//        currentPage = (index == Constant.REFRESHMORE ? 0:pageCount+1);
        this.index = index;
        page = currentPage+"";
        getExpressPresenter.getExpress(token,currentPage,filter,index);
    }

    @Override
    public void showToast(String msg) {
//        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
        isLoading = false;
        if(msg.equals("没有更多数据") && index == Constant.LOADMORE){
            isEnd = true;
//            loadMore.setVisibility(View.INVISIBLE);
            Log.e(TAG,"is end,"+"mData.size="+mData.size());
        }
    }

    @Override
    public void showLoading(int index) {
        switch (index){
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

    @Override
    public void showData(List<DeliveryDatum> delivery) {
        Log.e(TAG,"page::"+page);
        isLoading = false;
        if(index == Constant.REFRESHMORE){
            mAdapter.clear();
            Log.e(TAG,"add to head");
            mData.clear();
            mData.addAll(delivery);
            mScrollListener.setCurrentPage(0);
            isEnd = false;
            Log.e(TAG,"下拉刷新完成");
        } else{
            Log.e(TAG,"add to end");
            mData.addAll(delivery);
            mScrollListener.setloadFinish(true);
            Log.e(TAG,"上拉加载完成,page"+currentPage);
        }
        mAdapter.notifyDataSetChanged();
        Log.e(TAG,"currentItem :"+mAdapter.getItemCount());
        Log.e(TAG,"page:"+page+",size:"+delivery.size());
        for(DeliveryDatum datum:delivery){
            Log.e(TAG,datum.getName());
        }
    }

    private void sendTipsMsg(String msg,String userId){
        TextMessage myTextMessage = TextMessage.obtain(msg);

        //"7127" 为目标 Id。根据不同的 conversationType，可能是用户 Id、讨论组 Id、群组 Id 或聊天室 Id。
        //Conversation.ConversationType.PRIVATE 为会话类型。
        Message myMessage = null;
        if(userId.equals(Constant.SYSTEM_USER_ID)){
            myMessage = Message.obtain(userId, Conversation.ConversationType.SYSTEM, myTextMessage);
        }else {
            myMessage = Message.obtain(userId, Conversation.ConversationType.PRIVATE, myTextMessage);
        }

        /**
         * <p>发送消息。
         * 通过 {@link IRongCallback.ISendMessageCallback}
         * 中的方法回调发送的消息状态及消息体。</p>
         *
         * @param message     将要发送的消息体。
         * @param pushContent 当下发 push 消息时，在通知栏里会显示这个字段。
         *                    如果发送的是自定义消息，该字段必须填写，否则无法收到 push 消息。
         *                    如果发送 sdk 中默认的消息类型，例如 RC:TxtMsg, RC:VcMsg, RC:ImgMsg，则不需要填写，默认已经指定。
         * @param pushData    push 附加信息。如果设置该字段，用户在收到 push 消息时，能通过 {@link io.rong.push.notification.PushNotificationMessage#getPushData()} 方法获取。
         * @param callback    发送消息的回调，参考 {@link IRongCallback.ISendMessageCallback}。
         */
        RongIM.getInstance().sendMessage(myMessage, null, null, new IRongCallback.ISendMessageCallback() {
            @Override
            public void onAttached(Message message) {
                //消息本地数据库存储成功的回调
            }

            @Override
            public void onSuccess(Message message) {
                //消息通过网络发送成功的回调
            }

            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                //消息发送失败的回调
            }
        });
    }
}
