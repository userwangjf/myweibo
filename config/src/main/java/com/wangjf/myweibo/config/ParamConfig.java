package com.wangjf.myweibo.config;

import android.content.Context;

import com.wangjf.myutils.SharedPreferencesUtils;

/**
 * Created by wangjf on 17-11-23.
 */

public class ParamConfig {

    private static Context mContext;
    private static ParamConfig mParamConfig;
    private static String mUrlHost = "http://192.168.1.100/myweibo/";
    private static String mTokenid = "";
    private static boolean mAdminLogin = false;



    public static ParamConfig get(Context ctx) {
        if(mContext == null) {
            mContext = ctx;
            mParamConfig = new ParamConfig();
        }
        LoadParam();
        return mParamConfig;
    }

    public static void LoadParam() {
        if(mContext != null) {
            String ret = SharedPreferencesUtils.init(mContext).getString("mUrlHost",null);
            if(ret != null) mUrlHost = ret;
            ret = SharedPreferencesUtils.init(mContext).getString("mTokenid",null);
            if(ret != null) mTokenid = ret;
            mAdminLogin = SharedPreferencesUtils.init(mContext).getBoolean("mAdminLogin",false);
        }
    }

    public static void SaveParam() {
        if(mContext != null) {
            SharedPreferencesUtils.init(mContext).putString("mUrlHost",mUrlHost);
            SharedPreferencesUtils.init(mContext).putString("mTokenid",mTokenid);
            SharedPreferencesUtils.init(mContext).putBoolean("mAdminLogin",mAdminLogin);
        }
    }

    public static void setUrlHost(String url) {
        mUrlHost = url;
        SaveParam();
    }

    public static String getUrlHost() {
        return mUrlHost;
    }

    public static void setTokenid(String tokenid) {
        mTokenid = tokenid;
        SaveParam();
    }

    public static String getTokenid() {
        return mTokenid;
    }


    public static boolean ismAdminLogin() {
        return mAdminLogin;
    }

    public static void setmAdminLogin(boolean mAdminLogin) {
        ParamConfig.mAdminLogin = mAdminLogin;
        SaveParam();
    }

    private ParamConfig() {
        // avoiding instantiation
    }

}
