#include <stdio.h>
#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_leachchen_testjni_MainActivity_testMethod(JNIEnv *env, jobject instance, jstring name_) {
    const char *name = (*env)->GetStringUTFChars(env, name_, 0);
    char buff[128] = {0};
    sprintf(buff,"I am from C part String and get java part String:%s",name);
    (*env)->ReleaseStringUTFChars(env, name_, name);

    return (*env)->NewStringUTF(env, buff);
}

