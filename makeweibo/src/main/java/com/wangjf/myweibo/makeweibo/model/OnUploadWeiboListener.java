package com.wangjf.myweibo.makeweibo.model;

/**
 * Created by wangjf on 17-11-23.
 */

public interface OnUploadWeiboListener {

    void onSuccess(String json);

    void onFailure(String msg);

}
