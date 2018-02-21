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
    private static String mTokenid = "";
    private static String mAdminLogin = "";
    private static String mUserId = "";

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
            mUrlHost = SharedPreferencesUtils.init(mContext).getString("mUrlHost","http://192.168.1.100/myweibo/");
            mTokenid = SharedPreferencesUtils.init(mContext).getString("mTokenid","");
            mAdminLogin = SharedPreferencesUtils.init(mContext).getString("mAdminLogin","");
            mUserId = SharedPreferencesUtils.init(mContext).getString("mUserId","");
        }
    }

    public static void LoginOut() {
        SharedPreferencesUtils.init(mContext).clear();
        LoadParam();
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
