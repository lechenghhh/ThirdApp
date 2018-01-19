package com.lecheng.hello.thirdapp.Utils;

import java.io.OutputStream;

/**
 * 用root权限执行Linux下的Shell指令
 *
 * @author jzj
 * @since 2014-09-09
 */
public class RootShellCmd {

    private OutputStream os;

    /**
     * 执行shell指令
     *
     * @param cmd 指令
     */
    public final void exec(String cmd) {
        System.out.println("shell: " + cmd);
        try {
            if (os == null) {
                os = Runtime.getRuntime().exec("su").getOutputStream();
            }
            os.write(cmd.getBytes());
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 后台模拟全局按键
     *
     * @param keyCode 键值
     */
    public final void simulateKey(int keyCode) {
        exec("input keyevent " + keyCode + "\n");
    }

    //input tap 100 200  #在屏幕坐标(100, 200)处点击
    public final void simulateTap(int x, int y) {
        exec("input tap " + x + " " + y + "\n");
    }
} 