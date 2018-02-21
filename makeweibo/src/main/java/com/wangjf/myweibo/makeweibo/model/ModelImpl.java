package com.wangjf.myweibo.makeweibo.model;


import android.util.Log;

import com.guozheng.urlhttputils.urlhttp.CallBackUtil;
import com.guozheng.urlhttputils.urlhttp.UrlHttpUtil;
import com.wangjf.myutils.MyLogUtils;
import com.wangjf.myweibo.config.ParamConfig;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wangjf on 17-11-21.
 */

public class ModelImpl implements ModelIntf {

    @Override
    public void addWeibo(String weiboJson, String picJson, List<File> picfs, final OnModelListener listener) {

        String UrlMakeWeibo = String.format("%s/%s", ParamConfig.getUrlHost(),
                "?service=weibo.makeweibo");
        MyLogUtils.d("makeWeibo::ModelImpl: " + UrlMakeWeibo);

        Map<String,String> params = new HashMap<>();
        params.put("weibo",weiboJson);
        params.put("tokenid",ParamConfig.getTokenid());
        if(picJson != null)
            params.put("pics",picJson);

        UrlHttpUtil.uploadListFile(UrlMakeWeibo, picfs,  "uploadfile[]", UrlHttpUtil.FILE_TYPE_FILE, params, new CallBackUtil.CallBackString() {
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
}
