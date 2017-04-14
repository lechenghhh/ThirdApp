package com.lecheng.hello.thirdapp.ActivityList;

/**
 * Created by Cheng on 2017/2/24.
 */

public class Aty041MVP_Model implements Aty041Interface_Model {
    String strData = "";

    @Override
    public void setData(String str) {
        strData = str;
        System.out.println("显示：：：：" + str);
    }

    @Override
    public String getData() {
        return strData;
    }


}
