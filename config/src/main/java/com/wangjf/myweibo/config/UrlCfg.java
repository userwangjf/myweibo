package com.wangjf.myweibo.config;

/**
 * Created by wangjf on 17-11-23.
 */

public class UrlCfg {

    private static String mUrlHost = "http://192.168.43.2/myweibo/";
    //private static String mUrlHost = "http://192.168.1.103/myweibo/";
    //private static String mUrlHost = "http://10.40.251.68/myweibo/";
    private static String mUrlGetWeibo = mUrlHost + "";

    public static String getUrlHost() {
        return mUrlHost;
    }

    private UrlCfg() {
        // avoiding instantiation
    }

}
