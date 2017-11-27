package com.example.adaministrator.myapplication.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;

/**
 * Created by Adaministrator on 2017/11/27.
 */

public class AidlClient {

    private Context context;
    private IBookManager mService;
    private boolean mIsBound;

    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            // This is called when the connection with the service has been
            // established, giving us the service object we can use to
            // interact with the service.  We are communicating with our
            // service through an IDL interface, so get a client-side
            // representation of that from the raw service object.
            mService = IBookManager.Stub.asInterface(service);

            // We want to monitor the service for as long as we are
            // connected to it.
            try {
                mService.registerCallback(mCallback);
            } catch (RemoteException e) {
                // In this case the service has crashed before we could even
                // do anything with it; we can count on soon being
                // disconnected (and then reconnected if it can be restarted)
                // so there is no need to do anything here.
            }
        }

        public void onServiceDisconnected(ComponentName className) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            mService = null;
        }
    };

    private IRespCallBack mCallback = new IRespCallBack.Stub() {
        @Override
        public void onResp(int code, String content) throws RemoteException {
            mHandler.sendMessage(mHandler.obtainMessage(BUMP_MSG, code, 0));
        }
        /**
         * This is called by the remote service regularly to tell us about
         * new values.  Note that IPC calls are dispatched through a thread
         * pool running in each process, so the code executing here will
         * NOT be running in our main thread like most other things -- so,
         * to update the UI, we need to use a Handler to hop over there.
         */
        //        public void valueChanged(int value) {
        //            mHandler.sendMessage(mHandler.obtainMessage(BUMP_MSG, value, 0));
        //        }

    };


    public AidlClient(Context context) {
        this.context = context;
    }

    public void bind() {
        Intent intent = new Intent();
        intent.setAction("com.example.adaministrator.myapplication.aidl.AidlService");
        intent.setPackage("com.example.adaministrator.myapplication");
        context.startService(intent);
        context.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    public void unbind() {
        if (mIsBound) {
            // If we have received the service, and hence registered with
            // it, then now is the time to unregister.
            if (mService != null) {
                try {
                    mService.unregisterCallback(mCallback);
                } catch (RemoteException e) {
                    // There is nothing special we need to do if the service
                    // has crashed.
                }
            }

            // Detach our existing connection.
            context.unbindService(mConnection);
            mIsBound = false;
        }
    }

    private static final int BUMP_MSG = 1;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BUMP_MSG:
                    // TODO: 2017/11/27 显示消息的内容
                    break;
                default:
                    super.handleMessage(msg);
            }
        }

    };
}
