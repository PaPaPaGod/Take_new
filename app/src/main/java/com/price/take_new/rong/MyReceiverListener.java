package com.price.take_new.rong;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.price.take_new.App;
import com.price.take_new.R;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

/**
 * Created by Administrator on 2016/9/24.
 */
public class MyReceiverListener extends PushMessageReceiver implements RongIMClient.OnReceiveMessageListener {
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onReceived(Message message, int i) {
        Context context = App.getContext();
        String userId = message.getSenderUserId();
        Log.e("uId",message.getSenderUserId());
        showNotification(context,userId);
//        Log.e("listener",userId+"-----"+message.getMessageId());
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void showNotification(Context context, String userId){
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);
        Log.e("msg","onClick");
        Intent intent = new Intent(context, NotificationSplashActivity.class);
        intent.putExtra("noti",userId);
        intent.putExtra("username",sendName);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        builder.setContentText(sendName+"给你发来一条信息");
        builder.setContentIntent(pendingIntent);
        builder.setContentTitle(sendName);
        builder.setAutoCancel(true);
        builder.setSmallIcon(R.mipmap.launcher_icon);
        Notification notification = builder.build();
        manager.notify(0,notification);
    }

    private String sendName;
    private String userId;

    @Override
    public boolean onNotificationMessageArrived(Context context, PushNotificationMessage pushNotificationMessage) {
        sendName = pushNotificationMessage.getSenderName();
        userId = pushNotificationMessage.getSenderId();
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onNotificationMessageClicked(Context context, PushNotificationMessage pushNotificationMessage) {
//        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        Notification.Builder builder = new Notification.Builder(context);
//        Log.e("msg","onClick");
//        Intent intent = new Intent(context, NotificationSplashActivity.class);
//        intent.putExtra("noti",userId);
//        intent.putExtra("username",sendName);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
//        builder.setContentText(sendName+"给你发来一条信息");
//        builder.setContentIntent(pendingIntent);
//        builder.setContentTitle(sendName);
//        builder.setAutoCancel(true);
//        builder.setSmallIcon(R.mipmap.launcher_icon);
//        Notification notification = builder.build();
//        manager.notify(0,notification);
        return true;
    }
}
