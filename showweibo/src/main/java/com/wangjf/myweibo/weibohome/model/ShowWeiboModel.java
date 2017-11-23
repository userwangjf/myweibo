package com.wangjf.myweibo.weibohome.model;


import android.util.Log;
import android.widget.Toast;

import com.guozheng.urlhttputils.urlhttp.CallBackUtil;
import com.guozheng.urlhttputils.urlhttp.UrlHttpUtil;

import java.util.HashMap;

/**
 * Created by wangjf on 17-11-21.
 */

public class ShowWeiboModel implements ShowWeiboModelIntf {

    private String UrlHost = "http://192.168.1.103/myweibo";
    private String UrlGetWeibo = UrlHost + "?service=getweibo&page=0&count=5";
    private String UrlGetWeiboMore = UrlHost + "?service=getweibo&page=1$count=5";

    @Override
    public void getWeibo(final OnLoadWeiboListener listener) {
        Log.d("WJF","start connect");
        //String url = "https://www.baidu.com/";
        //HashMap<String, String> paramsMap = new HashMap<>();
        //paramsMap.put("title","title");
        //paramsMap.put("desc","desc");
        UrlHttpUtil.post(UrlHost, null, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(int code, String errorMessage) {
                listener.onFailure(errorMessage);
            }

            @Override
            public void onResponse(String response) {
                listener.onSuccess(response);
            }
        });
    }

    @Override
    public void getWeiboMore(final OnLoadWeiboListener listener) {

    }





}
