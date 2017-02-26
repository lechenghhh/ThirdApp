package com.lecheng.hello.thirdapp.Bean.Aty040;

import java.util.List;

/**
 * Created by Cheng on 2017/2/25.
 */

public class BeanList {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * password : null
         * notetime : 2017-02-25 00:00:00.0
         * _id : 1
         * title : 二二绿F
         * content : 快的垃圾
         * account : null
         */

        private Object password;
        private String notetime;
        private int _id;
        private String title;
        private String content;
        private Object account;

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public String getNotetime() {
            return notetime;
        }

        public void setNotetime(String notetime) {
            this.notetime = notetime;
        }

        public int get_id() {
            return _id;
        }

        public void set_id(int _id) {
            this._id = _id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getAccount() {
            return account;
        }

        public void setAccount(Object account) {
            this.account = account;
        }
    }
}
