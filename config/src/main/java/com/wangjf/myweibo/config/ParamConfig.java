package com.wangjf.myweibo.config;

import android.content.Context;
import android.util.Log;

import com.wangjf.myutils.SharedPreferencesUtils;

/**
 * Created by wangjf on 17-11-23.
 */

public class ParamConfig {

    //用户参数
    private static String mUrlHost = "http://192.168.1.100/myweibo/";
    private static String mTokenid = "0";
    private static String mAdminLogin = "0";
    private static String mUserId = "0";

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

    public static String getmUserId() {
        return mUserId;
    }

    public static void setmUserId(String mUserId) {
        ParamConfig.mUserId = mUserId;
        SaveParam();
    }

    public static void LoadParam() {

        if(mContext != null) {
            String ret = SharedPreferencesUtils.init(mContext).getString("mUrlHost",null);
            if(ret != null) mUrlHost = ret;

            ret = SharedPreferencesUtils.init(mContext).getString("mTokenid",null);
            if(ret != null) mTokenid = ret;

            ret = SharedPreferencesUtils.init(mContext).getString("mAdminLogin",null);
            if(ret != null) mAdminLogin = ret;

            ret = SharedPreferencesUtils.init(mContext).getString("mUserId",null);
            if(ret != null) mUserId = ret;
        }
    }

    public static void SaveParam() {
        if(mContext != null) {
            SharedPreferencesUtils.init(mContext).putString("mUrlHost",mUrlHost);
            SharedPreferencesUtils.init(mContext).putString("mTokenid",mTokenid);
            SharedPreferencesUtils.init(mContext).putString("mAdminLogin",mAdminLogin);
            SharedPreferencesUtils.init(mContext).putString("mUserId",mUserId);
            Log.i("WJF","SaveParam mUrlHost: " + mUrlHost);
            Log.i("WJF","SaveParam mTokenid: " + mTokenid);
            Log.i("WJF","SaveParam mAdminLogin: " + mAdminLogin);
            Log.i("WJF","SaveParam mUserId: " + mUserId);
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

    public static String getmAdminLogin() {
        return mAdminLogin;
    }

    public static void setmAdminLogin(String mAdminLogin) {
        ParamConfig.mAdminLogin = mAdminLogin;
        SaveParam();
    }

    private ParamConfig() {
        // avoiding instantiation
    }

}
