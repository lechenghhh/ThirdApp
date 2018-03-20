package com.lecheng.hello.thirdapp.Bean.Json;

import java.util.List;

/**
 * Created by Cheng on 2017/3/18.
 */

public class Bean049 {

    /**
     * size : 10
     * list : [{"imgurl":"http://cms-bucket.nosdn.127.net/catchpic/5/54/545a53c04afecaab79a7caf34e6b07a6.jpg","has_content":true,"docurl":"http://war.163.com/17/0318/10/CFQ9PPGK000181KT.html","id":19391,"time":"2017-03-18 10:54:45","title":"歼-20服役的重大意义：中国作战体系核心环节成型","channelname":"war"},{"imgurl":"http://cms-bucket.nosdn.127.net/catchpic/1/13/13db9e339f9aa0c404b40f196f83dd6a.jpg","has_content":true,"docurl":"http://war.163.com/17/0318/10/CFQ9FMB5000181KT.html","id":19392,"time":"2017-03-18 10:49:14","title":"巴基斯坦试射远程反舰导弹：和印度导弹针锋相对","channelname":"war"},{"imgurl":"http://cms-bucket.nosdn.127.net/catchpic/8/82/82d2dcc0fc046c7cb0633968fe236a3a.jpg","has_content":true,"docurl":"http://war.163.com/17/0318/10/CFQ905RC000181KT.html","id":19373,"time":"2017-03-18 10:40:46","title":"韩国总统选举潜在竞选人呼吁取消部署＂萨德＂","channelname":"war"},{"imgurl":"http://cms-bucket.nosdn.127.net/catchpic/6/6c/6c2d2b8f61e30bb2e52a6d7ae2fcf290.jpg","has_content":true,"docurl":"http://war.163.com/17/0318/10/CFQ8JN3B000181KT.html","id":19372,"time":"2017-03-18 10:33:57","title":"特朗普预算猛增军费 国务院经费被砍受伤最深","channelname":"war"},{"imgurl":"http://cms-bucket.nosdn.127.net/catchpic/4/43/4345cac1633542d910ae7479dd27d363.jpg","has_content":true,"docurl":"http://war.163.com/17/0318/10/CFQ8DPMU000181KT.html","id":19374,"time":"2017-03-18 10:30:43","title":"日本再发射情报收集卫星 可监视朝鲜导弹发射动向","channelname":"war"},{"imgurl":"http://cms-bucket.nosdn.127.net/e778b4d57c8b42bdb317e5527bd687a120170318102647.jpeg","has_content":true,"docurl":"http://war.163.com/17/0318/10/CFQ811LA000181KT.html","id":19347,"time":"2017-03-18 10:23:46","title":"俄高官:解决朝鲜问题靠中俄 美为萨德找借口","channelname":"war"},{"imgurl":"http://cms-bucket.nosdn.127.net/7c2b535e2718496197f0548d59532b1c20170318105634.jpeg","has_content":true,"docurl":"http://war.163.com/17/0318/09/CFQ6HVOQ000181KN.html","id":19393,"time":"2017-03-18 09:48:00","title":"港媒：两岸关系可能恶化 台湾社会普遍忧虑","channelname":"war"},{"imgurl":"http://cms-bucket.nosdn.127.net/catchpic/d/d2/d20d94bde31f3e030fd53eb208e8b836.jpg","has_content":true,"docurl":"http://war.163.com/17/0318/09/CFQ4VHAF000181KT.html","id":19348,"time":"2017-03-18 09:30:30","title":"俄罗斯副外长:在北方四岛部署军队属于俄主权","channelname":"war"},{"imgurl":"http://cms-bucket.nosdn.127.net/1fed7ce15e79434ca0543ff0e05af0cb20170318102735.jpeg","has_content":true,"docurl":"http://war.163.com/17/0318/09/CFQ49VCO000181KN.html","id":19349,"time":"2017-03-18 09:18:44","title":"美媒:解放军重视可再生能源 增强独立作战能力","channelname":"war"},{"imgurl":"http://cms-bucket.nosdn.127.net/catchpic/d/d4/d4d4b9b068dbc7a5fea474957c65a58e.jpg","has_content":true,"docurl":"http://war.163.com/17/0318/09/CFQ3RT2T000181KT.html","id":19308,"time":"2017-03-18 09:11:03","title":"美驻联合国大使称美不打算重回六方会谈 中方回应","channelname":"war"}]
     */

    private int size;
    private List<ListBean> list;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * imgurl : http://cms-bucket.nosdn.127.net/catchpic/5/54/545a53c04afecaab79a7caf34e6b07a6.jpg
         * has_content : true
         * docurl : http://war.163.com/17/0318/10/CFQ9PPGK000181KT.html
         * id : 19391
         * time : 2017-03-18 10:54:45
         * title : 歼-20服役的重大意义：中国作战体系核心环节成型
         * channelname : war
         */

        private String imgurl;
        private boolean has_content;
        private String docurl;
        private int id;
        private String time;
        private String title;
        private String channelname;

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public boolean isHas_content() {
            return has_content;
        }

        public void setHas_content(boolean has_content) {
            this.has_content = has_content;
        }

        public String getDocurl() {
            return docurl;
        }

        public void setDocurl(String docurl) {
            this.docurl = docurl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getChannelname() {
            return channelname;
        }

        public void setChannelname(String channelname) {
            this.channelname = channelname;
        }
    }
}
