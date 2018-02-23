package com.wangjf.myweibo.makeweibo.model;


import android.util.Log;

import com.guozheng.urlhttputils.urlhttp.CallBackUtil;
import com.guozheng.urlhttputils.urlhttp.UrlHttpUtil;
import com.wangjf.myutils.MyLogUtils;
import com.wangjf.myweibo.config.ParamConfig;
import com.wangjf.taskmanager.ExecuteTask;
import com.wangjf.taskmanager.ExecuteTaskManager;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wangjf on 17-11-21.
 */

public class ModelImpl implements ModelIntf, ExecuteTaskManager.GetExcuteTaskCallback {

    private OnModelListener listener;

    @Override
    public void addWeibo(String weiboJson, String picJson, List<File> picfs, final OnModelListener listener) {

        this.listener = listener;

        //初始化网络访问
        ExecuteTaskManager.getInstance().init();
        AsyncUpload asyncUpload = new AsyncUpload();

        String UrlMakeWeibo = String.format("%s/%s", ParamConfig.getUrlHost(),
                "?service=weibo.makeweibo");
        MyLogUtils.d("makeWeibo::ModelImpl: " + UrlMakeWeibo);
        asyncUpload.setPostUrl(UrlMakeWeibo);

        Map<String,String> params = new HashMap<>();
        params.put("weibo",weiboJson);
        params.put("tokenid",ParamConfig.getTokenid());
        if(picJson != null) {
            params.put("pics", picJson);
        }
        asyncUpload.setPostParams(params);

        if(picfs != null) {
            asyncUpload.setPostFile(picfs);
        }


        ExecuteTaskManager.getInstance().getData(asyncUpload,this);

    }

    @Override
    public void onDataLoaded(ExecuteTask task) {
        if(task == null) {
            MyLogUtils.d("makeweibo::onDataLoaded:error");
            return ;
        }

        if(task.getStatus() > 0) {
            MyLogUtils.d("makeweibo::onDataLoaded:json " + task.getJson());
            listener.onSuccess(task.getJson());

        } else {
            MyLogUtils.d("makeweibo::onDataLoaded:error " + task.getMsg());
            listener.onFailure(task.getMsg());
        }

    }
}
