package com.wangjf.loginin.bean;

/**
 * Created by wangjf on 18-2-20.
 */

public class LogininBean {


    /**
     * ret : 200
     * data : {"tokenid":"6f9b15fbada8a3026db0a83ab05eb242"}
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
         * tokenid : 6f9b15fbada8a3026db0a83ab05eb242
         */

        private String tokenid;

        public String getTokenid() {
            return tokenid;
        }

        public void setTokenid(String tokenid) {
            this.tokenid = tokenid;
        }
    }
}
