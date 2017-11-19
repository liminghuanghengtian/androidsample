//
// Created by Adaministrator on 2017/11/19.
//

#include "com_example_moduleupgrade_HelloJni.h"
#include <stdio.h>

JNIEXPORT void JNICALL Java_com_example_moduleupgrade_HelloJni_sayHello(JNIEnv *env, jobject thiz){
    printf("invoke sayHello from C/n");
}
