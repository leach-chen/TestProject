#include <jni.h>


extern "C" JNIEXPORT jstring JNICALL
JNIEXPORT jstring JNICALL Java_com_leachchen_testjni_MainActivity_testMethod(JNIEnv *env, jobject instance, jint id,jstring name_) {
const char *name = (*env)->GetStringUTFChars(env, name_, 0);

// TODO

(*env)->ReleaseStringUTFChars(env, name_, name);

return (*env)->NewStringUTF(env, name);
}