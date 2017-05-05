package com.lecheng.hello.thirdapp.Bean.Gson;

import java.util.List;

/**
 * Created by 乐城 on 2017/4/10.
 */

public class Bean053FethG {
    /**
     {
     "count": 10,
     "error": false,
     "results": [
     {
     "desc": "10.27",
     "ganhuo_id": "56cc6d1d421aa95caa70755f",
     "publishedAt": "2015-10-27T02:43:16.906000",
     "readability": "",
     "type": "\u798f\u5229",
     "url": "http://ww2.sinaimg.cn/large/7a8aed7bjw1exfffnlf2gj20hq0qoju9.jpg",
     "who": "\u5f20\u6db5\u5b87"
     },
     {
     "desc": "5.20\u3002\n520\u7231\u4f60\uff0c\u5c31\u7ed9\u4f60\u751c\u751c\u7684\u7b11\u3002\u4eca\u65e5\u7279\u63a8\uff01~~\uff08\u3065\uffe33\uffe3\uff09\u3065\u256d\u2764\uff5e",
     "ganhuo_id": "56cc6d1d421aa95caa7075c5",
     "publishedAt": "2015-05-21T10:05:06.527000",
     "readability": "",
     "type": "\u798f\u5229",
     "url": "http://ww1.sinaimg.cn/large/7a8aed7bgw1esahpyv86sj20hs0qomzo.jpg",
     "who": "\u5f20\u6db5\u5b87"
     },
     {
     "desc": "6.10\u2014\u2014\uff082\uff09",
     "ganhuo_id": "56cc6d1d421aa95caa7076df",
     "publishedAt": "2015-06-10T04:12:03.656000",
     "readability": "",
     "type": "\u798f\u5229",
     "url": "http://ww1.sinaimg.cn/large/7a8aed7bgw1esxxi1vbq0j20qo0hstcu.jpg",
     "who": "\u5f20\u6db5\u5b87"
     },
     {
     "desc": "6.10\u2014\u2014\uff081\uff09",
     "ganhuo_id": "56cc6d1d421aa95caa7076e1",
     "publishedAt": "2015-06-10T04:12:04.272000",
     "readability": "",
     "type": "\u798f\u5229",
     "url": "http://ww1.sinaimg.cn/large/7a8aed7bgw1esxxiw20rej20qo0hstcp.jpg",
     "who": "\u5f20\u6db5\u5b87"
     },
     {
     "desc": "6.18",
     "ganhuo_id": "56cc6d1d421aa95caa7076f8",
     "publishedAt": "2015-06-18T03:50:59.419000",
     "readability": "",
     "type": "\u798f\u5229",
     "url": "http://ww1.sinaimg.cn/large/7a8aed7bgw1et80fw2p80j20qo0hsdj1.jpg",
     "who": "\u5f20\u6db5\u5b87"
     },
     {
     "desc": "7.17",
     "ganhuo_id": "56cc6d1d421aa95caa707701",
     "publishedAt": "2015-07-17T03:43:22.394000",
     "readability": "",
     "type": "\u798f\u5229",
     "url": "http://ww3.sinaimg.cn/large/7a8aed7bgw1eu5jpf3lamj20f00mitck.jpg",
     "who": "\u5f20\u6db5\u5b87"
     },
     {
     "desc": "5.19\n",
     "ganhuo_id": "56cc6d1d421aa95caa707751",
     "publishedAt": "2015-05-20T03:57:51.477000",
     "readability": "",
     "type": "\u798f\u5229",
     "url": "http://ww2.sinaimg.cn/large/7a8aed7bgw1es8c7ucr0rj20hs0qowhl.jpg",
     "who": "\u5f20\u6db5\u5b87"
     },
     {
     "desc": "7.3",
     "ganhuo_id": "56cc6d1d421aa95caa70774e",
     "publishedAt": "2015-07-03T04:12:02.419000",
     "readability": "",
     "type": "\u798f\u5229",
     "url": "http://ww1.sinaimg.cn/large/7a8aed7bgw1etpfol394kj20qo0hsdiw.jpg",
     "who": "\u5f20\u6db5\u5b87"
     },
     {
     "desc": "8.18\u2014\u2014\uff081\uff09",
     "ganhuo_id": "56cc6d1d421aa95caa70779f",
     "publishedAt": "2015-08-18T03:58:47.771000",
     "readability": "",
     "type": "\u798f\u5229",
     "url": "http://ww1.sinaimg.cn/large/7a8aed7bgw1ev6jgvbt8ij20qo0hrdil.jpg",
     "who": "\u5f20\u6db5\u5b87"
     },
     {
     "desc": "8.18\u2014\u2014\uff082\uff09",
     "ganhuo_id": "56cc6d1d421aa95caa7077a0",
     "publishedAt": "2015-08-19T03:56:32.545000",
     "readability": "",
     "type": "\u798f\u5229",
     "url": "http://ww1.sinaimg.cn/large/7a8aed7bgw1ev6jh1hbsgj20hr0qoq5s.jpg",
     "who": "\u5f20\u6db5\u5b87"
     }
     ]
     }
     */

    private int count;
    private boolean error;
    private List<ResultsBean> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * desc : 10.27
         * ganhuo_id : 56cc6d1d421aa95caa70755f
         * publishedAt : 2015-10-27T02:43:16.906000
         * readability :
         * type : 福利
         * url : http://ww2.sinaimg.cn/large/7a8aed7bjw1exfffnlf2gj20hq0qoju9.jpg
         * who : 张涵宇
         */

        private String desc;
        private String ganhuo_id;
        private String publishedAt;
        private String readability;
        private String type;
        private String url;
        private String who;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getGanhuo_id() {
            return ganhuo_id;
        }

        public void setGanhuo_id(String ganhuo_id) {
            this.ganhuo_id = ganhuo_id;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getReadability() {
            return readability;
        }

        public void setReadability(String readability) {
            this.readability = readability;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
