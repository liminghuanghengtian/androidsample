package com.example.adaministrator.myapplication.lollipop;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.adaministrator.myapplication.R;
import com.example.adaministrator.myapplication.bilibili.BliBliActivity;

/**
 * Created by Adaministrator on 2017/11/12.
 */

public class FeatureTestActivity extends AppCompatActivity {

    private TextView mTvNdkContent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndk_test);
        mTvNdkContent = (TextView) findViewById(R.id.tv_ndk_content);
        // Log.i("Activity", "这行不会删除");
        android.util.Log.i("Activity", "原先的log日志打印语句已被删除");

        Notification.Builder builder = new Notification.Builder(this)
                .setContentTitle("New mail from liminghuang")// 标题
                .setContentText("今晚去哪里吃饭啊？")// 详细文本
                .setSmallIcon(R.mipmap.ic_launcher)//小图标
                .setColor(0xffff0000)
                .setAutoCancel(true);// 背景色

        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, BliBliActivity.class);
        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(FeatureTestActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);// 户点击抽屉式通知栏中的通知文本启动

        Notification.InboxStyle inboxStyle = new Notification.InboxStyle();
        String[] events = new String[6];
        // Sets a title for the Inbox in expanded layout
        inboxStyle.setBigContentTitle("Event tracker details:");
        // Moves events into the expanded layout
        for (int i = 0; i < events.length; i++) {

            inboxStyle.addLine(events[i]);
        }
        // Moves the expanded layout object into the notification object.
        builder.setStyle(inboxStyle);
        Notification noti = builder.build();
        NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0x1111, noti);
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        android.util.Log.i("FeatureTestActivity", "onNewIntent");
    }
}
