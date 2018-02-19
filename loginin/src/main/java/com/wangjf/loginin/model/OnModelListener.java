package com.wangjf.loginin.model;

/**
 * Created by wangjf on 17-11-23.
 */

public interface OnModelListener {

    void onSuccess(String json);

    void onFailure(String msg);

}
