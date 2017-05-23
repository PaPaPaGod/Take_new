package com.price.take_new.view.activity.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.takeretrofit.utils.ManagerData;
import com.price.take_new.AppActivity;
import com.price.take_new.BaseFragment;
import com.price.take_new.Constant;
import com.price.take_new.R;
import com.price.take_new.bean.UserInfo;
import com.price.take_new.presenter.GetUserInfoPresenter;
import com.price.take_new.rong.Rong;
import com.price.take_new.service.api.OnInfoChangeListener;
import com.price.take_new.service.viewService.IGetUserInfoView;
import com.price.take_new.service.viewService.ILoginView;
import com.price.take_new.utils.ManagerUserInfo;
import com.price.take_new.view.activity.notification.NotificationListActivity;
import com.price.take_new.view.fragment.home.Express_Plaza_Tab_Fragment;
import com.price.take_new.view.fragment.home.Personal_Center_Tab_Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * Created by price on 2/11/2017.
 */
public class HomeActivity extends AppActivity implements IGetUserInfoView, View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager mViewPager;

    private FragmentPagerAdapter mFragmentPagerAdapter;//将tab页面持久在内存中
    private Fragment mConversationList;
    private Fragment mConversationFragment = null;
    private List<Fragment> mFragment = new ArrayList<>();

    public static ImageButton ibtn_check;
    private RelativeLayout ry_toolbar;

    private Bundle sendToFragment ;

    private UserInfo info;

    private String token;
    private String ry_token;

    private GetUserInfoPresenter presenter;

    private Boolean isAuth;

    private int unReadMsgCount = 0;

    //    private ViewPagerIndicator mIndicator;
    private List<String> mTities = Arrays.asList("快递广场","消息","个人中心");
    private int images[] ={R.drawable.express_plaza_selector, R.drawable.message_selector, R.drawable.personal_center_selector,R.drawable.message_selector_red};

    public View getTabView(int position) {
        View v = LayoutInflater.from(HomeActivity.this).inflate(R.layout.tab_custom, null);
        TextView textView = (TextView) v.findViewById(R.id.tv_title);
        ImageView imageView = (ImageView) v.findViewById(R.id.iv_icon);
        textView.setText(mTities.get(position));
        imageView.setImageResource(images[position]);
        if(position == 1){
            if(unReadMsgCount == 0) {
                imageView.setImageResource(images[position]);
            }else if(unReadMsgCount !=0){
                imageView.setImageResource(images[3]);
            }
        }
        //添加一行，设置颜色
        textView.setTextColor(tabLayout.getTabTextColors());//
        return v;
    }

    private void initIndicator() {
        //配置ViewPager的适配器
        mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }

        };
        mViewPager.setAdapter(mFragmentPagerAdapter);
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return null;
//        return Express_Plaza_Tab_Fragment.newInstance();
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
        Bundle bundle = intent.getExtras();
        token = bundle.getString(Constant.KEY_TOKEN);
        ry_token = bundle.getString(Constant.KEY_RONG_TOKEN);
        presenter = new GetUserInfoPresenter(this);
        presenter.getUserInfo(token,this);
//        if(ManagerUserInfo.isNull(this)) {
//            presenter.getUserInfo(token);
//        }else{
//            info = ManagerUserInfo.getInfo(this);
//        }
        sendToFragment = new Bundle();
        sendToFragment.putString(Constant.KEY_TOKEN,token);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        isAuth = ManagerData.getAuth(this);
        if (null != getIntent()) {
            handleIntent(getIntent());
        }
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);

        ry_toolbar = (RelativeLayout) findViewById(R.id.ly_toolbar);
        if(ry_toolbar == null){
            Toast.makeText(this,"null",Toast.LENGTH_LONG).show();
            Log.e("null","null");
        }
        ibtn_check = (ImageButton) findViewById(R.id.btn_check);
        ibtn_check.setOnClickListener(this);

        Express_Plaza_Tab_Fragment express_plaza_tab_fragment = Express_Plaza_Tab_Fragment.newInstance();
        mFragment.add(Express_Plaza_Tab_Fragment.newInstance());//加入第一页
        express_plaza_tab_fragment.setArguments(sendToFragment);
        mConversationList = initConversationList();
        mFragment.add(mConversationList);//加入会话列表
        mFragment.add(Personal_Center_Tab_Fragment.getInstance());//加入第三页
        Personal_Center_Tab_Fragment.getInstance().setArguments(sendToFragment);

        tabLayout.setupWithViewPager(mViewPager);
        //设置自定义视图
        initIndicator();
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(getTabView(i));
        }
        addUnReadMessageCountChanged();
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                if (tab.equals(tabLayout.getTabAt(1))){
//                    ry_toolbar.setVisibility(View.VISIBLE);
//                }else{
//                    ry_toolbar.setVisibility(View.GONE);
//
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                if (tab.equals(tabLayout.getTabAt(1))){
//                    ry_toolbar.setVisibility(View.VISIBLE);
//                }else{
//                    ry_toolbar.setVisibility(View.GONE);
//
//                }
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//                if (tab.equals(tabLayout.getTabAt(1))){
//                    ry_toolbar.setVisibility(View.VISIBLE);
//                }else{
//                    ry_toolbar.setVisibility(View.GONE);
//
//                }
//            }
//        });
    }

    private void addUnReadMessageCountChanged() {
        RongIM.getInstance().setOnReceiveUnreadCountChangedListener(new RongIM.OnReceiveUnreadCountChangedListener() {
            @Override
            public void onMessageIncreased(int i) {
                Log.e("unreadcount",i+"");
                unReadMsgCount = i;
//                tabLayout.
                for (int j = 0; i < tabLayout.getTabCount(); i++) {
                    TabLayout.Tab tab = tabLayout.getTabAt(i);
                    tab.setCustomView(getTabView(j));
                }
            }
        },Conversation.ConversationType.PRIVATE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            moveTaskToBack(false);
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bindData(UserInfo info) {
        if(info!=null){
            this.info = info;
            Log.e("home_info",info.getName());
//            RongIM.getInstance().setCurrentUserInfo(new io.rong.imlib.model.UserInfo(RongIM.getInstance().getCurrentUserId(), info.getName(),
//                    Uri.parse("file://res/mipmap/male.png"))
//            );
//            RongIM.getInstance().setMessageAttachedUserInfo(true);
//            ManagerUserInfo.cacheInfo(this,info);
//            listener.onChange(info);
          Rong.connect(ry_token);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    private Fragment initConversationList() {
        /**
         * appendQueryParameter对具体的会话列表做展示
         */
        if (mConversationFragment == null) {
            ConversationListFragment listFragment = ConversationListFragment.getInstance();
            Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
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
    public void onClick(View v) {
//        if (isAuth){
        ibtn_check.setBackgroundResource(R.mipmap.ic_add_alert_black_18dp);
        Intent intent = new Intent(this, NotificationListActivity.class);
        intent.putExtra(Constant.KEY_TOKEN,token);
        startActivity(intent);
//        }else{
//            AlertDialog dialog = new AlertDialog.Builder(this)
//                    .setMessage("您尚未进行个人真实资料认证，认证后方能正常使用，是否现在认证？")
//                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            // TODO Auto-generated method stub
//                            Intent intent = new Intent(HomeActivity.this,AuthoActivity.class);
//                            intent.putExtra(Constant.KEY_TOKEN,token);
//                            startActivity(intent);
//                            dialog.dismiss();
//                        }
//                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    }).create();
//            dialog.show();
//        }
    }
}
