#include <stdio.h>
#include <jni.h>
#include <iostream>
#include "logHelp.h"

using namespace std;

#define CLASS_PATH_NAME	 "com/leachchen/testjni/MainActivity"

/**
 * 静态注册方式
 */
/*extern "C" JNIEXPORT jstring JNICALL
Java_com_leachchen_testjni_MainActivity_testMethod(JNIEnv *env, jobject instance, jstring name_) {
    const char *name = env->GetStringUTFChars(name_, 0);
    char buff[128] = {0};
    sprintf(buff,"I am from C part String and get java part String:%s",name);
    env->ReleaseStringUTFChars(name_, name);

    return env->NewStringUTF(buff);
}*/

/**
 * 动态注册方式
 */
jstring testJniMethod(JNIEnv *env, jobject instance, jstring name_) {
    const char *name = env->GetStringUTFChars(name_, 0);
    LOGE("get java value:%s",name);
    char buff[128] = {0};
    sprintf(buff,"I am from C part String and get java part String:%s",name);
    env->ReleaseStringUTFChars(name_, name);

    return env->NewStringUTF(buff);
}


//注册Java端的方法以及本地相对应的方法
JNINativeMethod method[]={
    {
          "testMethod", //Java中native函数的函数名
          "(Ljava/lang/String;)Ljava/lang/String;", // Java中的native对应的native签名
          (void *)testJniMethod //native 中的方法指针
     }
};

//注册相应的类以及方法
jint registerNativeMeth(JNIEnv *env){
    jclass cl=env->FindClass(CLASS_PATH_NAME);
    if((env->RegisterNatives(cl,method,sizeof(method)/sizeof(method[0])))<0){
        return -1;
    }
    return 0;
}

//实现jni_onload 动态注册方法
jint JNI_OnLoad(JavaVM* vm, void* reserved) {
    JNIEnv* env = NULL;
    if (vm->GetEnv((void**) &env, JNI_VERSION_1_4) != JNI_OK) {
        return -1;
    }
    if(registerNativeMeth(env)!=JNI_OK){//注册方法
        return -1;
    }
    return JNI_VERSION_1_4;//必须返回这个值
}


