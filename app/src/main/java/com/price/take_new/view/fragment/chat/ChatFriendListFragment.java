package com.price.take_new.view.fragment.chat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.takeretrofit.bean.friend.FriendDatum;
import com.price.take_new.App;
import com.price.take_new.BaseFragment;
import com.price.take_new.R;
import com.price.take_new.bean.UserInfo;
import com.price.take_new.bean.UserInfoDao;
import com.price.take_new.rong.Rong;
import com.price.take_new.utils.adapter.FriendListAdapter;
import com.price.take_new.utils.listener.FriendClickListener;
import com.price.take_new.view.fragment.express.PackageDetailFragment;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;

/**
 * Created by intel on 3/13/2018.
 */

public class ChatFriendListFragment extends BaseFragment {
    private static final String TAG = "ChatFriendListFragment";
    private RecyclerView mRecyclerView;
    private List<FriendDatum> mList;
    private FriendListAdapter mAdapter;
//    private List<>

    private static ChatFriendListFragment instance = new ChatFriendListFragment();
    private String userId ="446";
    private String userName = "unkwon";
    private SearchView mSearchView;

//    public static ChatFriendListFragment newInstance() {
//        if (instance == null) {
//            instance = new ChatFriendListFragment();
//        }
//        return instance;
//    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_chatfriendlist);
        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        //设置增加或删除条目的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mList = new ArrayList<>();
        initData();
        mRecyclerView.setAdapter(mAdapter);

        mSearchView = (SearchView) view.findViewById(R.id.chat_main_search);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (mSearchView != null) {
                    // 得到输入管理对象
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        // 这将让键盘在所有的情况下都被隐藏，但是一般我们在点击搜索按钮后，输入法都会乖乖的自动隐藏的。
                        imm.hideSoftInputFromWindow(mSearchView.getWindowToken(), 0); // 输入法如果是显示状态，那么就隐藏输入法
                    }
                    mSearchView.clearFocus(); // 不获取焦点
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String selection = ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY + " LIKE '%" + newText + "%' " + " OR "
                        + ContactsContract.RawContacts.SORT_KEY_PRIMARY + " LIKE '%" + newText + "%' ";
//                mCursor = getActivity().getContentResolver().query(ContactsContract.RawContacts.CONTENT_URI, PROJECTION, selection, null, null);
//                mAdapter.swapCursor(mCursor); // 交换指针，展示新的数据
                return true;
            }
        });
    }

    private void initData() {
        mList = new ArrayList<>();
        getList();
        mAdapter = new FriendListAdapter(getActivity(),mList,R.layout.item_friend,null);
        mAdapter.notifyDataSetChanged();
        mAdapter.setListener(new FriendClickListener() {
            @Override
            public void send() {
                Log.e(TAG,"send msg to friends");
                RongIM.getInstance().startPrivateChat(getActivity(),userId,userName);
            }
        });
    }

    private void getList() {
        // TODO: 3/15/2018  得到朋友列表
        FriendDatum friendDatum = new FriendDatum();
//        friendDatum.setId("22");
//        friendDatum.setName("tan");
//        friendDatum.setSex("男");
        mList.add(friendDatum);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chatfriendlist;
    }
}
