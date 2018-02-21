package com.wangjf.loginin.model;

import com.guozheng.urlhttputils.urlhttp.CallBackUtil;
import com.guozheng.urlhttputils.urlhttp.UrlHttpUtil;
import com.wangjf.myutils.MyLogUtils;
import com.wangjf.myweibo.config.ParamConfig;

import java.util.Map;

/**
 * Created by wangjf on 17-11-21.
 */

public class ModelImpl implements ModelIntf {


    @Override
    public void LoadBean(final OnModelListener listener, Map<String, String> params) {

        String urlLogin = ParamConfig.getUrlHost() + "?service=LoginIn.LoginIn";

        MyLogUtils.d("Loginin: " + urlLogin);

        UrlHttpUtil.post(urlLogin, params, new CallBackUtil.CallBackString() {
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
