package com.wangjf.myweibo.makeweibo.model;


import android.util.Log;

import com.guozheng.urlhttputils.urlhttp.CallBackUtil;
import com.guozheng.urlhttputils.urlhttp.RealResponse;
import com.guozheng.urlhttputils.urlhttp.UrlHttpUtil;
import com.wangjf.myweibo.config.UrlCfg;
import com.wangjf.myweibo.makeweibo.bean.MakeWeiboBean;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wangjf on 17-11-21.
 */

public class MakeWeiboModel implements MakeWeiboModelIntf {

    @Override
    public void addWeibo(String weiboJson, String picJson, List<File> picfs, final OnUploadWeiboListener listener) {

        String UrlMakeWeibo = String.format("%s/%s", UrlCfg.getUrlHost(),
                "?service=weibo.makeweibo");
        Log.i("WJF","makeWeibo: " + UrlMakeWeibo);

        Map<String,String> params = new HashMap<>();
        params.put("weibo",weiboJson);
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
