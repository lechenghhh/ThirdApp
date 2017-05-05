package com.lecheng.hello.thirdapp.Bean;
/* http://www.jianshu.com/p/e7c2c63fa70e*/
public class JniTest{
    static {
        System.loadLibrary("jary");
    }
    public native String getString();
}