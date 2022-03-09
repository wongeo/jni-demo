#include <jni.h>
#include <string>
#include  <android/log.h>// 引入log头文件
//#include "Person.h"

#define  TAG    "这里填写日志的TAG"// log标签

// 定义debug信息
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
using namespace std;


extern "C" jstring
Java_com_feng_jnidemo_MainActivity_stringFromJNI(JNIEnv *env, jobject /* this */) {
    std::string hello = "Hello from C++";

    string sss = "abccc";


    LOGD("abcjifdjifdjifjdi");
    return env->NewStringUTF(sss.c_str());
}

