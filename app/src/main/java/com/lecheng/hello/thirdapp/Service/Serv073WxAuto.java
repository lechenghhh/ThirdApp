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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
//        System.out.println("Serv073-event=" + event);
//        System.out.println("Serv073-getEventType=" + event.getEventType());
//        System.out.println("Serv073-getSource=" + event.getSource());
        if (!contentDescription.equals(event.getSource().getContentDescription() + "")) {
            System.out.println("Serv073-getContentDescription=" + event.getSource().getContentDescription());
            if ((event.getSource().getContentDescription() + "").equals("")) {
                AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
                if (nodeInfo != null) {
                    //为了演示,直接查看了关闭按钮的id
                    List<AccessibilityNodeInfo> infos = nodeInfo.findAccessibilityNodeInfosByViewId("@id/anb");
                    nodeInfo.recycle();
                    for (AccessibilityNodeInfo item : infos) {
                        item.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    }
                }
            }
        }
        contentDescription = event.getSource().getContentDescription() + "";
//        System.out.println("Serv073-getText=" + event.getText());
//        System.out.println("Serv073-getClassName=" + event.getClassName());
//        System.out.println("Serv073-getText=" + event.getText());
//        System.out.println("Serv073-isEnabled=" + event.isEnabled());
//        System.out.println("Serv073-getItemCount=" + event.getItemCount());

/*抢红包代码触发器*/
//        switch (eventType) {
//            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
//                handleNotification(event);
//                break;
//            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
//            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
//                String className = event.getClassName().toString();
//                if (className.equals("com.tencent.mm.ui.LauncherUI")) {
//                    getPacket();
//                } else if (className.equals("com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyReceiveUI")) {
//                    openPacket();
//                } else if (className.equals("com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyDetailUI")) {
//                    close();
//                }
//                break;
//        }
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
    }

    /**
     * 处理通知栏信息
     * <p>
     * 如果是微信红包的提示信息,则模拟点击
     *
     * @param event
     */
    private void handleNotification(AccessibilityEvent event) {
        List<CharSequence> texts = event.getText();
        if (!texts.isEmpty()) {
            for (CharSequence text : texts) {
                String content = text.toString();
                //如果微信红包的提示信息,则模拟点击进入相应的聊天窗口
                if (content.contains("[微信红包]")) {
                    if (event.getParcelableData() != null && event.getParcelableData() instanceof Notification) {
                        Notification notification = (Notification) event.getParcelableData();
                        PendingIntent pendingIntent = notification.contentIntent;
                        try {
                            pendingIntent.send();
                        } catch (PendingIntent.CanceledException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * 关闭红包详情界面,实现自动返回聊天窗口
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void close() {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo != null) {
            //为了演示,直接查看了关闭按钮的id
            List<AccessibilityNodeInfo> infos = nodeInfo.findAccessibilityNodeInfosByViewId("@id/ez");
            nodeInfo.recycle();
            for (AccessibilityNodeInfo item : infos) {
                item.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }
    }

    /**
     * 模拟点击,拆开红包
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void openPacket() {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo != null) {
            //为了演示,直接查看了红包控件的id
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId("@id/b9m");
            nodeInfo.recycle();
            for (AccessibilityNodeInfo item : list) {
                item.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }
    }

    /**
     * 模拟点击,打开抢红包界面
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void getPacket() {
        AccessibilityNodeInfo rootNode = getRootInActiveWindow();
        AccessibilityNodeInfo node = recycle(rootNode);

        node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        AccessibilityNodeInfo parent = node.getParent();
        while (parent != null) {
            if (parent.isClickable()) {
                parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                break;
            }
            parent = parent.getParent();
        }

    }

    /**
     * 递归查找当前聊天窗口中的红包信息
     * <p>
     * 聊天窗口中的红包都存在"领取红包"一词,因此可根据该词查找红包
     *
     * @param node
     */
    public AccessibilityNodeInfo recycle(AccessibilityNodeInfo node) {
        if (node.getChildCount() == 0) {
            if (node.getText() != null) {
                if ("领取红包".equals(node.getText().toString())) {
                    return node;
                }
            }
        } else {
            for (int i = 0; i < node.getChildCount(); i++) {
                if (node.getChild(i) != null) {
                    recycle(node.getChild(i));
                }
            }
        }
        return node;
    }

}