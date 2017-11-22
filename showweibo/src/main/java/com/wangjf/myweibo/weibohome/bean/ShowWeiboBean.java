package com.wangjf.myweibo.weibohome.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangjf on 17-11-19.
 */

public class ShowWeiboBean implements Serializable {


    /**
     * ret : 200
     * data : {"weibo":[{"id":"74","type":"公开","content":"新的微博","isturn":"0","iscomment":"0","time":"1510577649","praise":"0","turn":"0","collect":"0","comment":"0","uid":"0","pic":[{"url":"2017/11/775202eb80751ed330e0e23c2a8f75ea.jpg","ctime":"1111"}]},{"id":"73","type":"公开","content":"新的微博","isturn":"0","iscomment":"0","time":"1510577353","praise":"0","turn":"0","collect":"0","comment":"0","uid":"0","pic":[{"url":"2017/11/a3541c5c25d835ed131624d4cdccb1df.jpg","ctime":"0"},{"url":"2017/11/87238299c07ab11768d00d769c3cb788.jpg","ctime":"0"}]},{"id":"72","type":"公开","content":"新的微博","isturn":"0","iscomment":"0","time":"1510577225","praise":"0","turn":"0","collect":"0","comment":"0","uid":"0","pic":[{"url":"2017/11/2340917b7fbdfdf91cea6aa4d879947a.jpg","ctime":"0"},{"url":"2017/11/f303e99c1be4a13f62a0b56ebd0bbd42.jpg","ctime":"0"}]},{"id":"71","type":"公开","content":"新的微博","isturn":"0","iscomment":"0","time":"1510577179","praise":"0","turn":"0","collect":"0","comment":"0","uid":"0","pic":[{"url":"http://192.168.1.101/phalapi/myweibo/uploads/2017/11/ab3fdd02855626783ae6610d32afeda5.jpg","ctime":"0"},{"url":"http://192.168.1.101/phalapi/myweibo/uploads/2017/11/bb7c67a6579167bc8d61a84c95c96dcf.jpg","ctime":"0"}]},{"id":"70","type":"公开","content":"新的微博","isturn":"0","iscomment":"0","time":"1510577074","praise":"0","turn":"0","collect":"0","comment":"0","uid":"0","pic":[]}]}
     * msg :
     */

    private int ret;
    private DataBean data;
    private String msg;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private List<WeiboBean> weibo;

        public List<WeiboBean> getWeibo() {
            return weibo;
        }

        public void setWeibo(List<WeiboBean> weibo) {
            this.weibo = weibo;
        }

        public static class WeiboBean {
            /**
             * id : 74
             * type : 公开
             * content : 新的微博
             * isturn : 0
             * iscomment : 0
             * time : 1510577649
             * praise : 0
             * turn : 0
             * collect : 0
             * comment : 0
             * uid : 0
             * pic : [{"url":"2017/11/775202eb80751ed330e0e23c2a8f75ea.jpg","ctime":"1111"}]
             */

            private String id;
            private String type;
            private String content;
            private String isturn;
            private String iscomment;
            private String time;
            private String praise;
            private String turn;
            private String collect;
            private String comment;
            private String uid;
            private List<PicBean> pic;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getIsturn() {
                return isturn;
            }

            public void setIsturn(String isturn) {
                this.isturn = isturn;
            }

            public String getIscomment() {
                return iscomment;
            }

            public void setIscomment(String iscomment) {
                this.iscomment = iscomment;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getPraise() {
                return praise;
            }

            public void setPraise(String praise) {
                this.praise = praise;
            }

            public String getTurn() {
                return turn;
            }

            public void setTurn(String turn) {
                this.turn = turn;
            }

            public String getCollect() {
                return collect;
            }

            public void setCollect(String collect) {
                this.collect = collect;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public List<PicBean> getPic() {
                return pic;
            }

            public void setPic(List<PicBean> pic) {
                this.pic = pic;
            }

            public static class PicBean {
                /**
                 * url : 2017/11/775202eb80751ed330e0e23c2a8f75ea.jpg
                 * ctime : 1111
                 */

                private String url;
                private String ctime;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getCtime() {
                    return ctime;
                }

                public void setCtime(String ctime) {
                    this.ctime = ctime;
                }
            }
        }
    }
}


