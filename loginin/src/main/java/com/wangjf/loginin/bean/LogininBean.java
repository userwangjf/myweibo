package com.wangjf.loginin.bean;

/**
 * Created by wangjf on 18-2-20.
 */

public class LogininBean {

    /**
     * ret : 200
     * data : {"admin":0,"tokenid":"5ccb980c4e0bec6b6f96ee7914e36e84"}
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
         * admin : 0
         * tokenid : 5ccb980c4e0bec6b6f96ee7914e36e84
         */

        private int admin;
        private String tokenid;

        public int getAdmin() {
            return admin;
        }

        public void setAdmin(int admin) {
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
