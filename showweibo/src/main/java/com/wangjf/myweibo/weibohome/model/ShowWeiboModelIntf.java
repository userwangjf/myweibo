package com.wangjf.myweibo.weibohome.model;

/**
 * Created by wangjf on 17-11-21.
 */

public interface ShowWeiboModelIntf {
    void getWeibo(final OnLoadWeiboListener listener);
    void getWeiboMore(final OnLoadWeiboListener listener);
}
