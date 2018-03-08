#include <stdio.h>
#include <jni.h>
#include <iostream>
using namespace std;


extern "C" JNIEXPORT jstring JNICALL
Java_com_leachchen_testjni_MainActivity_testMethod(JNIEnv *env, jobject instance, jstring name_) {
    const char *name = env->GetStringUTFChars(name_, 0);
    char buff[128] = {0};
    sprintf(buff,"I am from C part String and get java part String:%s",name);
    env->ReleaseStringUTFChars(name_, name);

    return env->NewStringUTF(buff);
}


