package com.feng.cpplib;

public class JNI {
    static {
        System.loadLibrary("cpp-lib");
    }

    public native String stringFromJNI();
}
