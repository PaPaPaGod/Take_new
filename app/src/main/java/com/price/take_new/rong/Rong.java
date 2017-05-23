package com.price.take_new.rong;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.example.takeretrofit.bean.otheruserinfo.OtherUserInfoData;
import com.example.takeretrofit.utils.ManagerData;
import com.price.take_new.App;
import com.price.take_new.bean.UserInfoDao;
import com.price.take_new.presenter.GetOtherUserInfoPresenter;
import com.price.take_new.presenter.GetUserInfoPresenter;
import com.price.take_new.service.viewService.IGetOtherUserInfoView;
import com.price.take_new.service.viewService.IGetUserInfoView;

import java.util.List;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;

/**
 * Created by price on 1/16/2017.
 */

public class Rong {

    private static final String tag = "rong_test";

    static UserInfoDao userInfoDao = App.getDaoSession().getUserInfoDao();

    public static void connect(String token) {
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {

                }

                @Override
                public void onSuccess(String userid) {
                    Log.e(tag, "connect success with " + userid+"  currentId::"+
                            RongIM.getInstance().getCurrentUserId()
                    );

                    //设置接收监听器，接到时加入发送者信息
                    RongIM.getInstance().setOnReceiveMessageListener(new RongIMClient.OnReceiveMessageListener() {
                        @Override
                        public boolean onReceived(Message message, int i) {
//                            nodeRed.setChatRed();
                            String senderId = message.getSenderUserId();
                            com.price.take_new.bean.UserInfo info = userInfoDao.queryBuilder()
                                    .where(UserInfoDao.Properties.Id.like(senderId)).unique();
                            //为空时插入数据库
                            if(info == null){
                                Log.e(tag,"get from net and insert start~");
                                GetOtherUserInfoPresenter presenter = new GetOtherUserInfoPresenter(new IGetOtherUserInfoView() {
                                    @Override
                                    public void onBind(OtherUserInfoData info) {
                                        Log.e(tag,"bind sender msg finish");
                                        userInfoDao.insert(new com.price.take_new.bean.UserInfo(
                                                Long.parseLong(info.getId()),
                                                info.getPhoneNum(),
                                                info.getName(),
                                                info.getSex(),
                                                info.getSchoolName(),
                                                info.getMajor(),
                                                "","",""
                                        ));
                                    }

                                    @Override
                                    public void showToast(String msg) {
                                        Log.e(tag,msg);
                                    }
                                });
                                presenter.getUserInfo(ManagerData.getCachedToken(App.getContext()),senderId);
                            }
                            return false;
                        }
                    });

                    RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
                        @Override
                        public UserInfo getUserInfo(String s) {
                            boolean isMe = false;
                            Log.e(tag,"request id is "+s);
                            if(s.equals(RongIM.getInstance().getCurrentUserId())){
                                Log.e(tag,"request id is currentUser");
                                isMe = true;
                            }
                            UserInfo info = findUserFromDao(s);
                            if(info!=null){
                                Log.e(tag,"get userInfo from dao successfully");
                                return info;
                            }else{
                                if(isMe){
                                    //get my info from net
                                    getUserMsg(s);
                                }else{
                                    //get other user info from net
                                    findOtherUserFromNet(s);
                                }
                            }
                            return null;
                        }
                    }, true);
                }

            private UserInfo getUserMsg(String userId) {
                com.price.take_new.bean.UserInfo myInfo = null;
                myInfo = userInfoDao.queryBuilder().where(UserInfoDao.Properties.Id.like(userId)).unique();
                if(myInfo!=null){
                    return new UserInfo(Long.toString(myInfo.getId()),myInfo.getName(),null);
                }else {
                    GetUserInfoPresenter presenter = new GetUserInfoPresenter(new IGetUserInfoView() {
                        @Override
                        public void showToast(String msg) {
                            Log.e(tag, "getUserMsg error " + msg);
                        }

                        @Override
                        public void bindData(final com.price.take_new.bean.UserInfo info) {
                            Log.e(tag, "getUserMsg's name:: " + info.getName());
                            userInfoDao.insertOrReplace(info);
                            RongIM.getInstance().refreshUserInfoCache(new UserInfo(Long.toString(info.getId()), info.getName(), null));
                        }

                        @Override
                        public void hideLoading() {

                        }

                        @Override
                        public void showLoading() {

                        }
                    });
                    presenter.getUserInfo(ManagerData.getCachedToken(App.getContext()), App.getContext());
                }
                return null;
            }

            private void findOtherUserFromNet(String userId) {
                GetOtherUserInfoPresenter presenter = new GetOtherUserInfoPresenter(new IGetOtherUserInfoView() {
                    @Override
                    public void onBind(OtherUserInfoData info) {
                        Log.e(tag,"findUserFromDao the result is "+info.getName());
                        RongIM.getInstance().refreshUserInfoCache(new UserInfo(info.getId(),info.getName(),null));
                    }

                    @Override
                    public void showToast(String msg) {
                        Log.e(tag,"findUserFromNet error "+msg);
                    }
                });
                String token = ManagerData.getCachedToken(App.getContext());
                if(!TextUtils.isEmpty(token)){
                    presenter.getUserInfo(token,userId);
                }
            }

            private UserInfo findUserFromDao(String userId) {
                    Log.e(tag,"findUserFromDao's id is "+userId);
                    //获取数据库中我要查找的好友的bean对象
                    UserInfo userInfo = null;
                    com.price.take_new.bean.UserInfo info = userInfoDao.queryBuilder().
                            where(UserInfoDao.Properties.Id.like(userId)).unique();
                    if(info!=null){
                        Log.e(tag,"findUserFromDao the result is "+info.getName());
                        userInfo = new UserInfo(userId,info.getName(),null);
                    }
                    return userInfo;
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                }
            });
        }

    public interface NodeRed{
        void setChatRed();
    }
}
