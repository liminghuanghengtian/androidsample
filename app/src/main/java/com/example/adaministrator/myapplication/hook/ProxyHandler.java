package com.example.adaministrator.myapplication.hook;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ProjectName：MyApplication
 * PackageName: com.example.adaministrator.myapplication.hook
 * Description:
 * <p>
 * CreateTime: 2017/12/17 19:08
 * Modifier: Adaministrator
 * ModifyTime: 2017/12/17 19:08
 * Comment:
 *
 * @author Adaministrator
 */

public class ProxyHandler implements InvocationHandler {

    private Object tar;

    //绑定委托对象，并返回代理类
    public Object bind(Object tar) {
        this.tar = tar;
        //绑定该类实现的所有接口，取得代理类
        return Proxy.newProxyInstance(tar.getClass().getClassLoader(), tar.getClass().getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        // 这里就可以进行所谓的AOP编程了
        // 在调用具体函数方法前，执行功能处理
        String methodName = method.getName();
        Log.i("invoke", "before invoke " + methodName);
        result = method.invoke(tar, args);
        Log.i("invoke", "after invoke " + methodName);
        //在调用具体函数方法后，执行功能处理
        return result;
    }

    //    public static void main(String args[]) {
    //        ProxyHandler proxy = new ProxyHandler();
    //        //绑定该类实现的所有接口
    //        Subject sub = (Subject) proxy.bind(new RealSubject());
    //        sub.doSomething("my name is huangliming");
    //        sub.undoSomething("my name is liminghuang");
    //    }
}
