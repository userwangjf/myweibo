package com.wangjf.myweibo.makeweibo.bean;

import java.util.List;

/**
 * Created by wangjf on 17-11-27.
 */

//TODO wid没有实现
public class MakeWeiboBean {
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

    public MakeWeiboBean() {
        id = "";
        type = "";
        content = "";
        isturn = "";
        iscomment = "";
        time = "";
        praise = "";
        turn = "";
        collect = "";
        comment = "";
        uid = "";
    }

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


}