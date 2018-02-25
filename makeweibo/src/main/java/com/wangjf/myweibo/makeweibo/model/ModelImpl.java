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
    public void addWeibo(String weiboContext, List<String> picPath, final OnModelListener listener) {

        this.listener = listener;

        //初始化网络访问
        ExecuteTaskManager.getInstance().init();
        AsyncUpload asyncUpload = new AsyncUpload();

        String UrlMakeWeibo = String.format("%s/%s", ParamConfig.getUrlHost(),
                "?service=weibo.makeweibo");
        MyLogUtils.d("makeWeibo::ModelImpl: " + UrlMakeWeibo);
        asyncUpload.setPostUrl(UrlMakeWeibo);

        asyncUpload.setWeiboText(weiboContext);
        asyncUpload.setPicPath(picPath);
        asyncUpload.setTokenid(ParamConfig.getTokenid());

        ExecuteTaskManager.getInstance().getData(asyncUpload,this);

    }

    @Override
    public void onDataLoaded(ExecuteTask task) {
        if(task == null) {
            MyLogUtils.d("error: null pointer");
            return ;
        }

        if(task.getStatus() > 0) {
            MyLogUtils.d("get json: " + task.getJson());
            listener.onSuccess(task.getJson());

        } else {
            MyLogUtils.d("get err: " + task.getMsg());
            listener.onFailure(task.getMsg());
        }

    }
}
