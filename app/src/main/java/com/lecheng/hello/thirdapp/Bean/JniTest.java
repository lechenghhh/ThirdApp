package com.lecheng.hello.thirdapp.Bean;
/* http://www.jianshu.com/p/e7c2c63fa70e
*  http://www.open-open.com/lib/view/open1451917048573.html
*  */
public class JniTest{
    static {
        System.loadLibrary("jary");
    }
    public native String getString();
}