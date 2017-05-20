package com.price.take_new.rong;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.price.take_new.App;
import com.price.take_new.R;

import io.rong.imkit.RongIM;
import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

/**
 * Created by price on 2/25/2017.
 */

public class MyNotificationReceiver extends PushMessageReceiver {
    private String sendName;
    private String userId;
    private NotificationManager manager;
    private Notification notification;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onNotificationMessageArrived(Context context, PushNotificationMessage pushNotificationMessage) {
        sendName = pushNotificationMessage.getSenderName();
        userId = pushNotificationMessage.getSenderId();
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);
        Log.e("aaa","onClick");
        Intent intent = new Intent(context, NotificationSplashActivity.class);
        intent.putExtra("noti",userId);
        intent.putExtra("username",sendName);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        builder.setContentText(sendName+"send msg to you");
//        builder.setContentIntent(pendingIntent);
        builder.setContentTitle(sendName);
        builder.setAutoCancel(true);
        builder.setSmallIcon(R.mipmap.launcher_icon);
        notification = builder.build();
        manager.notify(0,notification);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onNotificationMessageClicked(Context context, PushNotificationMessage pushNotificationMessage) {
        Log.e("aaa","click");
//        if (RongIM.getInstance() != null) {
//            RongIM.getInstance().startPrivateChat(context, pushNotificationMessage.getSenderId(), pushNotificationMessage.getSenderName());
//        }
//        manager.notify(0,notification);
        Intent intent = new Intent(context, NotificationSplashActivity.class);
        intent.putExtra("noti",pushNotificationMessage.getSenderId());
        intent.putExtra("username",pushNotificationMessage.getSenderName());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        return true;
    }
}
