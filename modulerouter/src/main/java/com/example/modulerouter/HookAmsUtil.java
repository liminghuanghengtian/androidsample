package com.example.modulerouter;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * ProjectName：MyApplication
 * PackageName: com.example.modulerouter
 * Description:
 * <p>
 * CreateTime: 2017/11/29 21:20
 * Modifier: Adaministrator
 * ModifyTime: 2017/11/29 21:20
 * Comment：
 *
 * @author Adaministrator
 */

public class HookAmsUtil {
    public void hookSystemHandler() {
        try {

            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
            currentActivityThreadMethod.setAccessible(true);
            //获取主线程对象
            Object activityThread = currentActivityThreadMethod.invoke(null);
            //获取mH字段
            Field mH = activityThreadClass.getDeclaredField("mH");
            mH.setAccessible(true);
            //获取Handler
            Handler handler = (Handler) mH.get(activityThread);
            //获取原始的mCallBack字段
            Field mCallBack = Handler.class.getDeclaredField("mCallback");
            mCallBack.setAccessible(true);
            //这里设置了我们自己实现了接口的CallBack对象
            mCallBack.set(handler, new ActivityThreadHandlerCallback(handler)) ;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class ActivityThreadHandlerCallback implements Handler.Callback {

        private Handler handler;

        private ActivityThreadHandlerCallback(Handler handler) {
            this.handler = handler;
        }

        @Override
        public boolean handleMessage(Message msg) {
            Log.i("HookAmsUtil", "handleMessage");
            //替换之前的Intent
            if (msg.what ==100) {
                Log.i("HookAmsUtil","launchActivity");
                handleLauchActivity(msg);
            }

            handler.handleMessage(msg);
            return true;
        }

        private void handleLauchActivity(Message msg) {
            Object obj = msg.obj;//ActivityClientRecord
            try{
                Field intentField = obj.getClass().getDeclaredField("intent");
                intentField.setAccessible(true);
                Intent proxyInent = (Intent) intentField.get(obj);
                Intent realIntent = proxyInent.getParcelableExtra("oldIntent");
                if (realIntent != null) {
                    proxyInent.setComponent(realIntent.getComponent());
                }
            }catch (Exception e){
                Log.i("HookAmsUtil","lauchActivity falied");
            }
        }
    }

    // 在Application#onCreate中调用
    //这个ProxyActivity在清单文件中注册过，以后所有的Activitiy都可以用ProxyActivity无需声明，绕过监测
    //    HookAmsUtil hookAmsUtil = new HookAmsUtil(ProxyActivity.class, this);
    //        hookAmsUtil.hookSystemHandler();
    //        hookAmsUtil.hookAms();
}
