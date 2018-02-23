package com.wangjf.myweibo.makeweibo.model;

import com.guozheng.urlhttputils.urlhttp.RealResponse;
import com.guozheng.urlhttputils.urlhttp.UrlHttpUtil;
import com.guozheng.urlhttputils.urlhttp.syncHttpUtil;
import com.wangjf.myutils.MyLogUtils;
import com.wangjf.taskmanager.ExecuteTask;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 异步压缩图片，并完成异步上传
 * Created by wangjf on 18-2-23.
 */

public class AsyncUpload extends ExecuteTask {

    private Map<String,String>  postParams;
    private List<File>          postFile;
    private String              postUrl;

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public void setPostParams(Map<String,String> postParams) {
        this.postParams = postParams;
    }

    public void setPostFile(List<File> postFile) {
        this.postFile = postFile;
    }

    @Override
    public ExecuteTask doTask() {

        RealResponse response = syncHttpUtil.uploadListFile(postUrl,postFile,"uploadfile[]", UrlHttpUtil.FILE_TYPE_FILE, postParams);

        MyLogUtils.d("makeweibo::doTask: " + response.code);
        if(syncHttpUtil.isHttpOk(response)) {

            setStatus(200);
            setJson(syncHttpUtil.getSucdessMessage(response));
            setMsg("");
        } else {
            setStatus(0);
            setMsg(syncHttpUtil.getErrorMessage(response));
        }

        return this;
    }
}
