package com.wangjf.myweibo.weibohome.model;


import android.util.Log;
import android.widget.Toast;

import com.guozheng.urlhttputils.urlhttp.CallBackUtil;
import com.guozheng.urlhttputils.urlhttp.UrlHttpUtil;
import com.wangjf.myweibo.config.UrlCfg;

import java.util.HashMap;

/**
 * Created by wangjf on 17-11-21.
 */

public class ShowWeiboModel implements ShowWeiboModelIntf {

    private int page = 0;
    private int count = 5;

    @Override
    public void getWeibo(final OnLoadWeiboListener listener) {
        page = 0;
        count = 5;
        String UrlGetWeibo = String.format("%s/%s%s%d%s%d",UrlCfg.getUrlHost(),"?service=weibo.getweibo",
                "&page=",page,"&count=",count);
        Log.i("WJF","getWeibo: " + UrlGetWeibo);
        UrlHttpUtil.post(UrlGetWeibo, null, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(int code, String errorMessage) {
                listener.onFailure(errorMessage);
            }

            @Override
            public void onResponse(String response) {
                listener.onSuccess(0,response);
            }
        });
    }

    @Override
    public void getWeiboMore(final OnLoadWeiboListener listener) {
        page += count;
        String UrlGetWeiboMore = String.format("%s/%s%s%d%s%d",UrlCfg.getUrlHost(),"?service=weibo.getweibo",
                "&page=",page,"&count=",count);
        Log.i("WJF","getWeibo: " + UrlGetWeiboMore);
        UrlHttpUtil.post(UrlGetWeiboMore, null, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(int code, String errorMessage) {
                listener.onFailure(errorMessage);
            }

            @Override
            public void onResponse(String response) {
                listener.onSuccess(1,response);
            }
        });
    }





}
