package com.wangjf.myweibo.config;

import android.content.Context;

import com.wangjf.myutils.SharedPreferencesUtils;

/**
 * Created by wangjf on 17-11-23.
 */

public class ParamConfig {

    private static String mUrlHost = "http://192.168.1.100/myweibo/";
    private static Context mContext;
    private static ParamConfig mParamConfig;

    public static ParamConfig get(Context ctx) {
        if(mContext == null) {
            mContext = ctx;
            mParamConfig = new ParamConfig();
        }
        LoadParam();
        return mParamConfig;
    }

    public static String getUrlHost() {
        return mUrlHost;
    }

    public static void LoadParam() {
        if(mContext != null) {
            String ret = SharedPreferencesUtils.init(mContext).getString("mUrlHost",null);
            if(ret != null) setUrlHost(ret);
        }
    }

    public static void SaveParam() {
        if(mContext != null) {
            SharedPreferencesUtils.init(mContext).putString("mUrlHost",mUrlHost);
        }
    }

    public static void setUrlHost(String url) {
        mUrlHost = url;
        SaveParam();
    }

    private ParamConfig() {
        // avoiding instantiation
    }

}
