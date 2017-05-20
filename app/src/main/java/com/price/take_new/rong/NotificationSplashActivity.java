package com.price.take_new.rong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import io.rong.imkit.RongIM;

/**
 * 通知栏点击后的跳转事件
 * Created by Administrator on 2016/9/24.
 */
public class NotificationSplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String userId = intent.getStringExtra("noti");
        String userName = intent.getStringExtra("username");
        if (RongIM.getInstance() != null) {
            RongIM.getInstance().startPrivateChat(NotificationSplashActivity.this, userId, userName);
        }
        NotificationSplashActivity.this.finish();
    }
}
