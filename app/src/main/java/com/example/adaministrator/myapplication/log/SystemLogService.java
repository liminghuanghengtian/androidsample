package com.example.adaministrator.myapplication.log;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;

/**
 * Created by Adaministrator on 2017/11/4.
 */

public class SystemLogService extends IntentService {
    public static final String TAG = SystemLogService.class.getSimpleName();
    private Handler mHandler;

    public SystemLogService() {
        super(TAG);
    }

    public void onCreate() {
        super.onCreate();
        mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case 0x11:// 发送请求
                        // TODO: 2017/11/4 解析数据，定时执行获取日志的任务，无法获取到ServiceHandler，无法执行定时任务
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        this.setIntentRedelivery(true);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // TODO: 2017/11/4 获取系统日志，然后发送一个消息给主线程handler
        Message msg = Message.obtain(mHandler, 0x11);
        msg.sendToTarget();
    }
}
