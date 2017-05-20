package com.price.take_new.view.activity.home;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.takeretrofit.bean.otheruserinfo.OtherUserInfoData;
import com.example.takeretrofit.model.GetOtherUserInfo;
import com.example.takeretrofit.utils.ManagerData;
import com.price.take_new.Constant;
import com.price.take_new.HomeBaseActivity;
import com.price.take_new.R;
import com.price.take_new.bean.UserInfo;
import com.price.take_new.presenter.AcceptExpressPresenter;
import com.price.take_new.presenter.GetOtherUserInfoPresenter;
import com.price.take_new.presenter.GetUserInfoPresenter;
import com.price.take_new.service.viewService.AcceptExpressView;
import com.price.take_new.service.viewService.IGetOtherUserInfoView;
import com.price.take_new.service.viewService.IGetUserInfoView;

import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;

/**
 * Created by price on 2/21/2017.
 */
public class ExpressDetailActivity extends HomeBaseActivity implements View.OnClickListener, AcceptExpressView, IGetOtherUserInfoView, IGetUserInfoView {
    private Button takeButton;

    private ImageButton im_noti;

    private TextView tv_company;
    private TextView tv_address;    //快递地点
    private TextView tv_take_time;
    private TextView tv_place;      //交换点
    private TextView tv_description;//修改添加的描述属性
    private TextView tv_money;

    private AcceptExpressPresenter presenter;
    private GetOtherUserInfoPresenter otherUserInfoPresenter;
    private GetUserInfoPresenter userInfoPresenter;

    OtherUserInfoData info;
    UserInfo myInfo;
    UserInfo otherInfo;

    private String poster_id ;//发布者id
    private String token;   //使用者token
    private String order_id;    //订单id
    private String company;
    private String address;
    private String take_time;
    private String place;
    private String description;
    private String money;

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
//        bundle = intent.getExtras();
        showData(intent);
    }

    private void showData(Intent intent) {
        if(intent!=null){
            token = intent.getStringExtra(Constant.KEY_TOKEN);
            poster_id = intent.getStringExtra(Constant.KEY_POSTER_ID);
            if(intent.getStringExtra(Constant.KEY_COMPANY)!=null)
                Log.e("detail_token",intent.getStringExtra(Constant.KEY_COMPANY));
            order_id = intent.getStringExtra(Constant.KEY_ORDER_ID);
            company = intent.getStringExtra(Constant.KEY_COMPANY);
            place = intent.getStringExtra(Constant.KEY_PLACE);
            take_time = intent.getStringExtra(Constant.KEY_TAKE_TIME);
            address = intent.getStringExtra(Constant.KEY_ADDRESS);
            description = intent.getStringExtra(Constant.KEY_DESCRIPTION);
            money = intent.getStringExtra(Constant.KEY_PRICE);
        }
    }

    @Override
    public void initView() {
        im_noti = (ImageButton) findViewById(R.id.btn_check);

        tv_company = (TextView) findViewById(R.id.express_company_detail);
        tv_address = (TextView) findViewById(R.id.express_location_detail);
        tv_take_time = (TextView) findViewById(R.id.express_take_time_detail);
        tv_place = (TextView) findViewById(R.id.express_trade_detail);
        tv_money = (TextView) findViewById(R.id.express_trade_bounty_price);
        tv_description = (TextView) findViewById(R.id.express_trade_description);

        takeButton = (Button) findViewById(R.id.take_button);
        takeButton.setOnClickListener(this);

        presenter = new AcceptExpressPresenter(this);
        otherUserInfoPresenter = new GetOtherUserInfoPresenter(this);
        userInfoPresenter = new GetUserInfoPresenter(this);
        otherUserInfoPresenter.getUserInfo(token,poster_id);
        userInfoPresenter.getUserInfo(token,this);

        tv_company.setText(company);
        tv_place.setText(place);
        tv_take_time.setText(take_time);
        tv_address.setText(address);
        tv_description.setText(description);
        tv_money.setText("￥ "+money);
    }

    @Override
    public String getToolBarTitle() {
        return "快递详情";
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_express_detail;
    }

    @Override
    public void onClick(View v) {
        presenter.showDialog(Constant.CODE_TAKE);
    }

    @Override
    public void showData(Bundle data) {

    }

    @Override
    public void showToast(String msg, int code) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        if(code == Constant.SUCCESS_WITH_MSG){
            setFinish();
            finish();
        }
    }

    @Override
    public void showMyDialog(int code) {
        switch (code){
            case Constant.CODE_TAKE:
                AlertDialog.Builder takeBuilder = buildDialog("确定要代拿吗？");
                takeBuilder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(otherInfo!=null) {
                            presenter.acceptOrder(token, poster_id, order_id, otherInfo);
                        }
                        dialog.dismiss();
                    }
                });
                takeBuilder.create().show();
                break;
            case Constant.CODE_TALK:
                HomeActivity.ibtn_check.setBackgroundResource(R.mipmap.ic_add_alert_black_24dp_red);
                sendTipsMsg("Hello! 我叫 "+myInfo.getName()+",很高兴能帮助你拿快递，我的手机号码是： "
                        +myInfo.getPhoneNum()+" ，可随时找我哦~",poster_id);
//                im_noti.setBackgroundResource(R.drawable.rc_add_people);
                AlertDialog.Builder talkBuilder = buildDialog("是否要立刻与TA交流？");
                talkBuilder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toChat();
                        //我自己发给我
//                        sendTipsMsg("友情提示：亲爱的"+myInfo.getName()+"！你好！很高兴你能帮助 "+info.getName()+" 拿快递，TA的手机号码是： "
//                                +info.getPhoneNum()+" ，祝你好运~",Constant.SYSTEM_USER_ID+"");

                        //我发给别人
                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        setFinish();
                        ExpressDetailActivity.this.finish();
                    }
                });
                talkBuilder.create().show();
                break;
        }

    }

    private AlertDialog.Builder buildDialog(String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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

    private void toChat(){
        if (RongIM.getInstance() != null) {
            RongIM.getInstance().startPrivateChat(this, poster_id, info.getName());
        }
        setFinish();
        ExpressDetailActivity.this.finish();
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
         * 通过 {@link io.rong.imlib.IRongCallback.ISendMessageCallback}
         * 中的方法回调发送的消息状态及消息体。</p>
         *
         * @param message     将要发送的消息体。
         * @param pushContent 当下发 push 消息时，在通知栏里会显示这个字段。
         *                    如果发送的是自定义消息，该字段必须填写，否则无法收到 push 消息。
         *                    如果发送 sdk 中默认的消息类型，例如 RC:TxtMsg, RC:VcMsg, RC:ImgMsg，则不需要填写，默认已经指定。
         * @param pushData    push 附加信息。如果设置该字段，用户在收到 push 消息时，能通过 {@link io.rong.push.notification.PushNotificationMessage#getPushData()} 方法获取。
         * @param callback    发送消息的回调，参考 {@link io.rong.imlib.IRongCallback.ISendMessageCallback}。
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
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bindData(UserInfo info) {
        myInfo = info;
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoading() {

    }

    private void setFinish(){
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
    }

}
