package com.price.take_new.view.fragment.express;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.price.take_new.service.viewService.AcceptExpressView;
import com.price.take_new.service.viewService.IGetOtherUserInfoView;
import com.price.take_new.service.viewService.IGetUserInfoView;
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

public class PackageDetailFragment extends BaseFragment implements AcceptExpressView, IGetOtherUserInfoView, IGetUserInfoView {
    private static final String TAG = "PackageDetailFragment";

    private TextView tv_company;
    private TextView tv_addr;
    private TextView tv_trade;
    private TextView tv_remarks;
    private TextView tv_desc;
    private TextView tv_reward;
    private TextView tv_isInschool;

    private LinearLayout ly_reward;

    private Button btn_take;

    private String token;
    private String company;
    private String addr;
    private String trade;
    private String remarks;
    private String desc;
    private String reward;
    private String price;
    private String poster_id;
    private String order_id;

    private OtherUserInfoData info;
    private UserInfo myInfo;
    private UserInfo otherInfo;

    private AcceptExpressPresenter presenter;
    private GetOtherUserInfoPresenter otherUserInfoPresenter;
    private GetUserInfoPresenter userInfoPresenter;

    private Toolbar mToolbar;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initToolbar(view);
        setToolbarTitle("我的快递");
        token = ManagerData.getCachedToken(getActivity());
        tv_company = (TextView) view.findViewById(R.id.package_detail_company);
        tv_addr = (TextView) view.findViewById(R.id.package_detail_addr);
        tv_trade = (TextView) view.findViewById(R.id.package_detail_trade);
        tv_remarks = (TextView) view.findViewById(R.id.package_detail_remarks);
        tv_desc = (TextView) view.findViewById(R.id.package_detail_desc);
        tv_reward = (TextView) view.findViewById(R.id.package_detail_reward);
        tv_isInschool = (TextView) view.findViewById(R.id.package_detail_atschool);
        btn_take = (Button) view.findViewById(R.id.package_detail_take);
        btn_take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Constant.STATUS_FINISH!=ManagerData.getAuth(getActivity())){
                    check();
                    return;
                }
                presenter.showDialog(Constant.CODE_TAKE);
            }
        });
        ly_reward = (LinearLayout) view.findViewById(R.id.package_detail_ly_reward);

        presenter = new AcceptExpressPresenter(this);
        otherUserInfoPresenter = new GetOtherUserInfoPresenter(this);
        userInfoPresenter = new GetUserInfoPresenter(this);

//        userInfoPresenter.getUserInfo(token,getActivity());
        showDetail();
    }

    private void showDetail() {
        Bundle bundle = getArguments();
        if(bundle!=null){
            company = bundle.getString(Constant.KEY_COMPANY);
            addr = bundle.getString(Constant.KEY_ADDRESS);
            trade = bundle.getString(Constant.KEY_PLACE);
            remarks = bundle.getString(Constant.KEY_REMARKS);
            desc = bundle.getString(Constant.KEY_DESCRIPTION);
            price = bundle.getString(Constant.KEY_PRICE);
            reward = bundle.getString(Constant.KEY_SMALL_REWARD);
            order_id = bundle.getString(Constant.KEY_ORDER_ID);

            tv_company.setText(company);
            tv_addr.setText(addr);
            tv_trade.setText(trade);
            tv_remarks.setText(remarks);
//            tv_reward.setText(reward);
//            tv_isInschool.setText(company);
            tv_desc.setText(company);

            if(reward.isEmpty() && price.isEmpty()){
                ly_reward.setVisibility(View.GONE);
            }else{
                ly_reward.setVisibility(View.VISIBLE);
                if(reward.isEmpty()){
                    tv_reward.setText(price);
                }else {
                    tv_reward.setText(reward);
                }
            }
        }


        otherUserInfoPresenter.getUserInfo(token,poster_id);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_package_detail;
    }

    @Override
    public void onBind(OtherUserInfoData info) {
        this.info = info;
        otherInfo = new UserInfo(Long.parseLong(info.getId()),
                info.getPhoneNum(),
                info.getName(),
                info.getSex(),
                info.getSchoolName(),
                info.getMajor(),
                "","","");
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bindData(UserInfo info) {
        myInfo = info;
        setToolbarTitle(myInfo.getName()+"的快递");
    }

    private void initToolbar(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.package_detail_toolbar);
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

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showData(Bundle data) {

    }

    @Override
    public void showToast(String msg, int code) {
        Log.e(TAG,msg);
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
        if(code == Constant.SUCCESS_WITH_MSG){
            removeFragment();
            getActivity().finish();
        }
    }

    @Override
    public void showMyDialog(int code) {
        switch (code){
            case Constant.CODE_TAKE:
                AlertDialog.Builder takeBuilder = buildDialog("确定要代领吗？");
                takeBuilder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.acceptOrder(token,order_id);
                        if(otherInfo!=null) {
                        }
                        dialog.dismiss();
                    }
                });
                takeBuilder.create().show();
                break;
//            case Constant.CODE_TALK:
////                HomeActivity.ibtn_check.setBackgroundResource(R.mipmap.ic_add_alert_black_24dp_red);
//                sendTipsMsg("Hello! 我叫 "+myInfo.getName()+",很高兴能帮助你拿快递，我的手机号码是： "
//                        +myInfo.getPhoneNum()+" ，可随时找我哦~",poster_id);
////                im_noti.setBackgroundResource(R.drawable.rc_add_people);
//                AlertDialog.Builder talkBuilder = buildDialog("是否要立刻与TA交流？");
//                talkBuilder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        toChat();
//                        //我自己发给我
////                        sendTipsMsg("友情提示：亲爱的"+myInfo.getName()+"！你好！很高兴你能帮助 "+info.getName()+" 拿快递，TA的手机号码是： "
////                                +info.getPhoneNum()+" ，祝你好运~",Constant.SYSTEM_USER_ID+"");
//
//                        //我发给别人
//                        dialog.dismiss();
//                    }
//                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                        setFinish();
//                        ExpressDetailActivity.this.finish();
//                    }
//                });
//                talkBuilder.create().show();
//                break;
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
            RongIM.getInstance().startPrivateChat(getActivity(), poster_id, info.getName());
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

    //todo 获得用户具体信息
    private void getUserInfo(){

    }
}


