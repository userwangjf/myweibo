package com.wangjf.loginin.model;

import com.guozheng.urlhttputils.urlhttp.CallBackUtil;
import com.guozheng.urlhttputils.urlhttp.UrlHttpUtil;

import java.util.Map;

/**
 * Created by wangjf on 17-11-21.
 */

public class ModelImpl implements ModelIntf {

    @Override
    public void LoadBean(final OnModelListener listener, Map<String, String> params) {

        String url = "";

        UrlHttpUtil.post(url, params, new CallBackUtil.CallBackString() {
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
