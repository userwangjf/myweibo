package com.wangjf.myweibo.weibohome.model;

import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by wangjf on 17-11-21.
 */

public class ShowWeiboModel implements ShowWeiboModelIntf {

    @Override
    public void getWeibo() {
        OkHttpUtils.get().url("http://192.168.")
    }

    @Override
    public void getWeiboMore() {

    }
}
