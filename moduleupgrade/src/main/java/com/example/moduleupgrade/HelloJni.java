package com.example.moduleupgrade;

/**
 * Created by Adaministrator on 2017/11/19.
 */

public class HelloJni {

    public native void sayHello();

    static {
        System.loadLibrary("jni-test");
    }
}
