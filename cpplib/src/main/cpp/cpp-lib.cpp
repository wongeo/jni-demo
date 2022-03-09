#include <jni.h>
#include <string>
#include "Person.h"

extern "C"
JNIEXPORT jstring JNICALL
Java_com_feng_cpplib_JNI_stringFromJNI(JNIEnv *env, jobject /* this */) {
    std::string hello = "Hello from C++";
    Person *p = new Person();
    p->_name.c_str();

    return env->NewStringUTF(hello.c_str());
}