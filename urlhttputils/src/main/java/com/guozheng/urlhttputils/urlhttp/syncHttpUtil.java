package com.guozheng.urlhttputils.urlhttp;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by wangjf on 18-2-23.
 */

public class syncHttpUtil {


    /**
     * post请求，上传多个文件，以list集合的形式
     * @param url：url
     * @param fileList：集合元素是File对象
     * @param fileKey：上传参数时fileList对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static RealResponse uploadListFile(String url, List<File> fileList, String fileKey, String fileType, Map<String, String> paramsMap) {
        RealResponse response = new RealRequest().uploadFile(url, null,fileList,null,fileKey,fileType,paramsMap,null,null);

        return response;
    }

    public static RealResponse post(final String url, final Map<String, String> paramsMap) {
        //RealResponse response = new RealRequest().postData(url, getPostBody(paramsMap,null),getPostBodyType(paramsMap,null),null);
        RealResponse response = new RealRequest().getData(url,null);

        return response;
    }

    public static boolean isHttpOk(RealResponse response) {
        if(response.code == HttpURLConnection.HTTP_OK){
            return true;
        } else {
            return false;
        }
    }

    public static String getSucdessMessage(RealResponse response) {
        if(response.code == HttpURLConnection.HTTP_OK){
            try {
                return getRetString(response.inputStream);
            } catch (Exception e) {
                throw new RuntimeException("failure");
            }
        } else {
            return "";
        }
    }

    public static String getErrorMessage(RealResponse response) {
        final String errorMessage;

        if(response.code == HttpURLConnection.HTTP_OK){
            return "";
        } else {
            if(response.inputStream != null){
                errorMessage = getRetString(response.inputStream);
            }else if(response.errorStream != null) {
                errorMessage = getRetString(response.errorStream);
            }else if(response.exception != null) {
                errorMessage = response.exception.getMessage();
            }else {
                errorMessage = "";
            }
            return errorMessage;
        }
    }

    private static String getRetString(InputStream is) {
        String buf;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            buf = sb.toString();
            return buf;

        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 得到post请求的body
     */
    private static String getPostBody(Map<String, String> params,String jsonStr) {//throws UnsupportedEncodingException {
        if(params != null){
            return getPostBodyFormParameMap(params);
        }else if(!TextUtils.isEmpty(jsonStr)){
            return jsonStr;
        }
        return null;
    }

    /**
     * 根据键值对参数得到body
     */
    private static String getPostBodyFormParameMap(Map<String, String> params) {//throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (first)
                    first = false;
                else
                    result.append("&");
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }
            return result.toString();
        } catch (UnsupportedEncodingException e) {
            return null;
        }

    }

    /**
     * 得到bodyType
     */
    private static String getPostBodyType(Map<String, String> paramsMap, String jsonStr) {
        if(paramsMap != null){
            //return "text/plain";不知为什么这儿总是报错。目前暂不设置(20170424)
            return null;
        }else if(!TextUtils.isEmpty(jsonStr)){
            return "application/json;charset=utf-8";
        }
        return null;
    }

}

