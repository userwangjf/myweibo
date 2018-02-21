package com.wangjf.loginin.bean;

/**
 * Created by wangjf on 18-2-20.
 */

public class LogininBean {

    /**
     * ret : 200
     * data : {"uid":"101","admin":"0","tokenid":"4642ace883587d5d6cc81935c9eb6f5c"}
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
        /**
         * uid : 101
         * admin : 0
         * tokenid : 4642ace883587d5d6cc81935c9eb6f5c
         */

        private String uid;
        private String admin;
        private String tokenid;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getAdmin() {
            return admin;
        }

        public void setAdmin(String admin) {
            this.admin = admin;
        }

        public String getTokenid() {
            return tokenid;
        }

        public void setTokenid(String tokenid) {
            this.tokenid = tokenid;
        }
    }
}
