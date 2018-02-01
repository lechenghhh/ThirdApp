package com.lecheng.hello.thirdapp.Service;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.os.Build;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

//教程地址：http://blog.csdn.net/dd864140130/article/details/51794318
public class Serv073WxAuto extends AccessibilityService {

    private String contentDescription = "", text = "";
    private AccessibilityEvent event;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
//        System.out.println("Serv073-event=" + event);
//        System.out.println("Serv073-getEventType=" + event.getEventType());
//        System.out.println("Serv073-getSource=" + event.getSource());
//        System.out.println("Serv073-getText=" + event.getText());
//        System.out.println("Serv073-getClassName=" + event.getClassName());
//        System.out.println("Serv073-getText=" + event.getText());
//        System.out.println("Serv073-isEnabled=" + event.isEnabled());
//        System.out.println("Serv073-getItemCount=" + event.getItemCount());
        this.event = event;
        if (!contentDescription.equals(event.getSource().getContentDescription() + "")) {
            System.out.println("Serv073-getContentDescription=" + event.getSource().getContentDescription());
            switch ((event.getSource().getContentDescription() + "")) {
                case "当前所在页面,详细资料":
//                    findTextToAction("发消息");
//                    findTextToAction("添加到通讯录");
                    break;
                case "当前所在页面,聊天信息":
//                    System.out.println("Serv073-close--------------");
//                    close();
//                    findTextToAction("查找聊天记录");
                    break;
                default:
                    AccessibilityNodeInfo rowNode = getRootInActiveWindow();
                    if (rowNode == null) {
                        System.out.println("根节点为空");
                        return;
                    } else {
                        recycle(rowNode);
                    }
                    System.out.println("==============================================");
            }
        }
        contentDescription = event.getSource().getContentDescription() + "";
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)//测试功能 获取页面的所有节点
    public void recycle(AccessibilityNodeInfo info) {
        System.out.println();
        if (info.getChildCount() == 0) {
            System.out.println("child widget----------------------------" + info.getClassName());
            System.out.println("showDialog:" + info.canOpenPopup());
            System.out.println("Text：" + info.getText());
            System.out.println("windowId:" + info.getWindowId());
        } else {
            for (int i = 0; i < info.getChildCount(); i++) {
                if (info.getChild(i) != null) {
                    recycle(info.getChild(i));
                }
            }
        }
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void findTextToAction(String str) {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo != null) {
            //为了演示,直接查看了关闭按钮的id
            List<AccessibilityNodeInfo> infos = nodeInfo.findAccessibilityNodeInfosByText(str);
            nodeInfo.recycle();
            for (AccessibilityNodeInfo item : infos) {
                item.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void sendMsg() {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo != null) {
            //为了演示,直接查看了关闭按钮的id
            List<AccessibilityNodeInfo> infos = nodeInfo.findAccessibilityNodeInfosByText("发消息");
            nodeInfo.recycle();
            for (AccessibilityNodeInfo item : infos) {
                item.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void close() {
        event.getSource().performAction(AccessibilityService.GLOBAL_ACTION_BACK);
//        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
//        if (nodeInfo != null) {
//            //为了演示,直接查看了关闭按钮的id
//            List<AccessibilityNodeInfo> infos = nodeInfo.findAccessibilityNodeInfosByViewId("@id/ez");
//            nodeInfo.recycle();
//            for (AccessibilityNodeInfo item : infos) {
//                item.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//            }
//        }
    }
}