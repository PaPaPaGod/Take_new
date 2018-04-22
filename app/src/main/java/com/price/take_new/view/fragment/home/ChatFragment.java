package com.price.take_new.view.fragment.home;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.price.take_new.BaseFragment;
import com.price.take_new.R;
import com.price.take_new.view.fragment.chat.ChatFriendListFragment;
import com.price.take_new.view.fragment.chat.ChatListFragment;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;


/**
 * Created by intel on 2/15/2018.
 */

public class ChatFragment extends BaseFragment {
    private ViewPager mViewpager;
    private TabLayout mTabLayout;
    private Toolbar mToolbar;
    private SearchView mSearchView;

    private FragmentPagerAdapter mFragmentPagerAdapter;
    private List<Fragment> mFragment = new ArrayList<>();

    private static ChatFragment instance = new ChatFragment();
    private CharSequence[] titles = {"消息","关注人"};
    private int images[] ={R.drawable.tab_friend_selector, R.drawable.tab_msg_selector};


    public static ChatFragment getInstance(){
        if(instance == null){
            instance = new ChatFragment();
        }
        return instance;
    }

    private ChatListFragment chatListFragment;
    private ChatFriendListFragment friendListFragment;
    private Fragment mConversationList;
    private Fragment mConversationFragment = null;


    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initToolbar(view);
        mViewpager = (ViewPager) view.findViewById(R.id.chat_viewpager);
        mTabLayout = (TabLayout) view.findViewById(R.id.chat_tab);
//        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        friendListFragment = new ChatFriendListFragment();
        mConversationList = initConversationList();
        if (mFragment.size()==0){
            mFragment.add(mConversationList);//加入第一页
            mFragment.add(friendListFragment);//加入第二页
        }
        mTabLayout.setupWithViewPager(mViewpager);
        initIndicator();
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(getTabView(i));
        }
    }

    private void initToolbar(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.chat_toolbar);
        getHoldingActivity().setSupportActionBar(mToolbar);

        toolBarTitle = (TextView) view.findViewById(R.id.toolbar_title);
        toolBarTitle.setText("市场");
        getHoldingActivity().getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setNavigationIcon(R.drawable.icon_notification_normal);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mRightImage = (ImageView) view.findViewById(R.id.toolbar_right_icon);
        mRightImage.setImageResource(R.drawable.add_friend);
        mRightImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public View getTabView(int position) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.item_chat_selector, null);
        ImageView imageView = (ImageView) v.findViewById(R.id.iv_chat_tab);
        imageView.setImageResource(images[position]);
        return v;
    }

    private void initIndicator() {
        //配置ViewPager的适配器
        mFragmentPagerAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
            }
        };
        mViewpager.setAdapter(mFragmentPagerAdapter);
    }

    private Fragment initConversationList() {
        /**
         * appendQueryParameter对具体的会话列表做展示
         */
        if (mConversationFragment == null) {
            ConversationListFragment listFragment = ConversationListFragment.getInstance();
            Uri uri = Uri.parse("rong://" + getActivity().getApplicationInfo().packageName).buildUpon()
                    .appendPath("conversationList")
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")//设置私聊会话是否聚合显示
                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//设置群组会话聚合显示
                    .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置系统会话非聚合显示
                    .build();
            listFragment.setUri(uri);
            return listFragment;
        } else {
            return mConversationFragment;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat_main;
    }
}
