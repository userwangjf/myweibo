package com.wangjf.signin.model;

import android.util.Log;

import com.guozheng.urlhttputils.urlhttp.CallBackUtil;
import com.guozheng.urlhttputils.urlhttp.UrlHttpUtil;
import com.wangjf.myweibo.config.ParamConfig;

import java.util.Map;

/**
 * Created by wangjf on 17-11-21.
 */

public class ModelImpl implements ModelIntf {


    @Override
    public void LoadBean(final OnModelListener listener, Map<String, String> params) {

        String requestType = params.get("requestType");
        params.remove("requestType");
        Log.i("WJF","Signin::LoadBean," + requestType);
        String urlLogin;

        if(requestType.equals("signin")) {
            urlLogin = ParamConfig.getUrlHost() + "?service=SignIn.SignIn";
        } else {
            urlLogin = ParamConfig.getUrlHost() + "?service=SignIn.SignCode";
            params.put("tokenid",ParamConfig.getTokenid());
        }

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
