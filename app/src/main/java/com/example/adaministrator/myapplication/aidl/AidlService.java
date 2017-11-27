package com.example.adaministrator.myapplication.aidl;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class AidlService extends Service {
    public AidlService() {
    }

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.example.adaministrator.myapplication.aidl.AidlService");
        intent.setPackage("com.example.adaministrator.myapplication");
        context.startService(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new AidlBinder();
    }
}
