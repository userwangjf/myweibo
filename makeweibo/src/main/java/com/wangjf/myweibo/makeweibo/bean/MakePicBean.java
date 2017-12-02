package com.wangjf.myweibo.makeweibo.bean;

import java.util.List;

/**
 * Created by wangjf on 17-12-1.
 */

public class MakePicBean {

    List<PicInfo> picInfos;

    public MakePicBean() {
        picInfos = null;
    }

    public void setPicInfos(List<PicInfo> picInfos) {
        this.picInfos = picInfos;
    }

    public List<PicInfo> getPicInfos() {
        return this.picInfos;
    }

    public static class PicInfo {
        private String fname;  //文件名
        private String ctime; //创建时间

        public PicInfo() {
            fname = null;
            ctime = null;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }
    }

}
