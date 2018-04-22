package com.price.take_new.view.fragment.personal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.takeretrofit.bean.otheruserinfo.OtherUserInfoData;
import com.example.takeretrofit.utils.ManagerData;
import com.price.take_new.BaseFragment;
import com.price.take_new.Constant;
import com.price.take_new.R;
import com.price.take_new.bean.UserInfo;
import com.price.take_new.presenter.AcceptExpressPresenter;
import com.price.take_new.presenter.GetOtherUserInfoPresenter;
import com.price.take_new.presenter.GetUserInfoPresenter;
import com.price.take_new.presenter.ManagerExpressPresenter;
import com.price.take_new.presenter.PublishExpressPresenter;
import com.price.take_new.service.viewService.AcceptExpressView;
import com.price.take_new.service.viewService.IGetOtherUserInfoView;
import com.price.take_new.service.viewService.IGetUserInfoView;
import com.price.take_new.service.viewService.IManagerExpressView;
import com.price.take_new.view.activity.PersonalMsgActivity;

import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;


/**
 * Created by intel on 2/19/2018.
 */

public class PackageMyDetailFragment extends BaseFragment implements IManagerExpressView {
    private static final String TAG = "PackageMyDetailFragment";

    private EditText et_tbname;
    private EditText et_trade;
    private EditText et_addr;
    private EditText et_des;
    private EditText et_sms;
    private EditText et_reward_money;
    private EditText et_reward_goods;

    private TextView tv_company;

    private LinearLayout ll_money;
    private LinearLayout ll_goods;

    private SwitchCompat mSwitch;
    private AppCompatSpinner mSpinner;

    private RadioGroup rg_atschool;
    private RadioGroup rg_size;
    private RadioGroup rg_reward;
    private RadioButton rb_money;
    private RadioButton rb_goods;

    private Button btn_delete;
    private Button btn_update;
    private Button btn_chat;

    private String token;
    private int size;
    private int atschool;
    private String tbname;
    private String trade;
    private String addr;
    private String des;
    private String sms;
    private String company;
    private String reward;
    private String poster_id;
    private String user_id;
    private String order_id;
    private String accepter_id;
    private String accepter_name;
    private String price;
    private String small_reward;
    private String nickname;
    private int weight_type;

    private Boolean rewardType;

    private ManagerExpressPresenter presenter;


    private OtherUserInfoData info;
    private UserInfo myInfo;
    private UserInfo otherInfo;

    private Toolbar mToolbar;

    private static PackageMyDetailFragment instance = new PackageMyDetailFragment();

    public static PackageMyDetailFragment newInstance() {
        if (instance == null) {
            instance = new PackageMyDetailFragment();
        }
        return instance;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initToolbar(view);
        setToolbarTitle("我的快递");
        token = ManagerData.getCachedToken(getActivity());
        tv_company = (TextView) view.findViewById(R.id.tv_myexpress_company);
        et_addr = (EditText) view.findViewById(R.id.et_myexpress_address);
        et_tbname = (EditText) view.findViewById(R.id.et_myexpress_tbname);
        et_trade = (EditText) view.findViewById(R.id.et_myexpress_trade);
        et_des = (EditText) view.findViewById(R.id.et_myexpress_des);
        et_sms = (EditText) view.findViewById(R.id.et_myexpress_sms);
        et_reward_goods = (EditText) view.findViewById(R.id.et_myexpress_goods);
        et_reward_money = (EditText) view.findViewById(R.id.et_myexpress_money);

        ll_goods = (LinearLayout) view.findViewById(R.id.ll_myexpress_goods);
        ll_money = (LinearLayout) view.findViewById(R.id.ll_myexpress_money);

        mSpinner = (AppCompatSpinner) view.findViewById(R.id.spinner_myexpress_companny);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                company = getActivity().getResources().getStringArray(R.array.express_company_array)[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        rg_size = (RadioGroup) view.findViewById(R.id.rg_myexpress_size);
        rg_size.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_myexpress_small:
                        size = 1;
                        break;
                    case R.id.rb_myexpress_mid:
                        size = 2;
                        break;
                    case R.id.rb_myexpress_big:
                        size = 3;
                        break;
                }
            }
        });

        rg_atschool = (RadioGroup) view.findViewById(R.id.rg_atschool);
        rg_atschool.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_myexpress_in:
                        atschool = 1;
                        break;
                    case R.id.rb_publish_notin:
                        atschool = 0;
                        break;
                }
            }
        });

        rb_money = (RadioButton) view.findViewById(R.id.rb_myexpress_money);
        rb_goods = (RadioButton) view.findViewById(R.id.rb_myexpress_goods);
        rg_reward = (RadioGroup) view.findViewById(R.id.rg_reward);
        rg_reward.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_myexpress_money:
                        Log.e(TAG,"show money");
                        ll_goods.setVisibility(View.GONE);
                        ll_money.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_myexpress_goods:
                        Log.e(TAG,"show goods");
                        ll_money.setVisibility(View.GONE);
                        ll_goods.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        btn_delete = (Button) view.findViewById(R.id.btn_myexpress_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.delete(token,order_id);
            }
        });
        btn_update = (Button) view.findViewById(R.id.btn_myexpress_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                presenter.update(token,company,des,addr,order_id,trade,nickname,size,atschool,reward,price,small_reward);
            }
        });
        btn_chat = (Button) view.findViewById(R.id.btn_myexpress_chat_with_him);
        btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toChat();
//                presenter.update(token,order_id,company,des,addr,trade,reward,null);
            }
        });
        presenter = new ManagerExpressPresenter(this);
        showDetail();
    }

    private void getData() {
        des = et_des.getText().toString();
        addr = et_addr.getText().toString();
        trade = et_trade.getText().toString();
        tbname = et_tbname.getText().toString();
        sms = et_sms.getText().toString();
        price = et_reward_money.getText().toString();
        small_reward = et_reward_goods.getText().toString();
    }

    private void showDetail() {
        Bundle bundle = getArguments();
        if(bundle!=null){
//            phone = bundle.getString(Constant.KEY_PHONE_NUM);
            company = bundle.getString(Constant.KEY_COMPANY);
            addr = bundle.getString(Constant.KEY_ADDRESS);
            trade = bundle.getString(Constant.KEY_PLACE);
//            remarks = bundle.getString(Constant.KEY_COMPANY);
            price = bundle.getString(Constant.KEY_PRICE);
            small_reward = bundle.getString(Constant.KEY_SMALL_REWARD);

            if(price=="0" && small_reward.isEmpty()){
                ll_goods.setVisibility(View.GONE);
                ll_money.setVisibility(View.GONE);
            }else{
                if(price!="0"){
                    rb_money.setChecked(true);
                    rb_goods.setChecked(false);
                    ll_goods.setVisibility(View.GONE);
                    ll_money.setVisibility(View.VISIBLE);
                    et_reward_money.setText(price);
                }else{
                    rb_money.setChecked(false);
                    rb_goods.setChecked(true);
                    ll_goods.setVisibility(View.VISIBLE);
                    ll_money.setVisibility(View.GONE);
                    et_reward_goods.setText(small_reward);
                }
            }
            des = bundle.getString(Constant.KEY_DES);
            sms = bundle.getString(Constant.KEY_SMS);
            tbname = bundle.getString(Constant.TB_NAME);
            accepter_id = bundle.getString(Constant.KEY_ACCEPTER_ID);
            accepter_name = bundle.getString(Constant.KEY_ACCEPTER_NAME);
            order_id = bundle.getString(Constant.KEY_ORDER_ID);
            tv_company.setText(company);
            et_addr.setText(addr);
            et_trade.setText(trade);
//            et_remarks.setText(remarks);
//            tv_reward.setText(reward);
//            tv_isInschool.setText(company);
            et_des.setText(des);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myexpress_detail;
    }


    private void initToolbar(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.myexpress_toolbar);
        getHoldingActivity().setSupportActionBar(mToolbar);

        toolBarTitle = (TextView) view.findViewById(R.id.toolbar_title);
        getHoldingActivity().getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setNavigationIcon(R.drawable.icon_back);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFragment();
            }
        });

        mRightImage = (ImageView) view.findViewById(R.id.toolbar_right_icon);
        mRightImage.setImageResource(R.drawable.icon_setting);
        mRightImage.setVisibility(View.GONE);
    }

    private void setToolbarTitle(String title){
        if(toolBarTitle!=null){
            toolBarTitle.setText(title);
        }
    }


    private AlertDialog.Builder buildDialog(String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(title);
        builder.setTitle("提示");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return builder;
    }

    private void check(){
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setMessage("您尚未进行个人真实资料认证，认证后方能正常使用，是否现在认证？")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Intent intent = new Intent(getActivity(),PersonalMsgActivity.class);
                        intent.putExtra(Constant.FRAGMENT_NAME,Constant.AUTHORIZATION);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(getActivity(),"认证后方能正常使用",Toast.LENGTH_SHORT).show();
                    }
                }).create();
        dialog.show();
    }

    private void toChat(){
        if (RongIM.getInstance() != null) {
            RongIM.getInstance().startPrivateChat(getActivity(), accepter_id, accepter_name);
        }
        removeFragment();
        getActivity().finish();
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

    @Override
    public void showToast(String msg, int code) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
        if(code == Constant.SUCCESS_WITH_MSG){
            removeFragment();
        }
    }

    @Override
    public void showDialog() {

    }
}


