package com.wangjf.myweibo.makeweibo.model;

import android.graphics.Bitmap;
import android.icu.text.LocaleDisplayNames;
import android.media.ExifInterface;
import android.util.Log;

import com.google.gson.Gson;
import com.guozheng.urlhttputils.urlhttp.RealResponse;
import com.guozheng.urlhttputils.urlhttp.UrlHttpUtil;
import com.guozheng.urlhttputils.urlhttp.syncHttpUtil;
import com.jkt.tcompress.TCompress;
import com.wangjf.myutils.MyLogUtils;
import com.wangjf.myweibo.config.ParamConfig;
import com.wangjf.myweibo.makeweibo.bean.MakePicBean;
import com.wangjf.myweibo.makeweibo.bean.MakeWeiboBean;
import com.wangjf.taskmanager.ExecuteTask;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 异步压缩图片，并完成异步上传
 * Created by wangjf on 18-2-23.
 */

public class AsyncUpload extends ExecuteTask {

    private Map<String,String>  postParams;
    private List<File>          postFile;
    private String              postUrl;

    //待上传的图片路径列表
    private List<String>        picPath;
    //微博内容
    private String              weiboText;
    //
    private String              tokenid;

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }

    public void setPicPath(List<String> picPath) {
        this.picPath = picPath;
    }

    public void setWeiboText(String weiboText) {
        this.weiboText = weiboText;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    @Override
    public ExecuteTask doTask() {

        //组织待上传的参数/文件
        MyLogUtils.d("compress start");
        makeWeibo(weiboText,picPath);
        MyLogUtils.d("compress finish");

        RealResponse response = syncHttpUtil.uploadListFile(postUrl,postFile,"uploadfile[]", UrlHttpUtil.FILE_TYPE_FILE, postParams);

        MyLogUtils.d("response code: " + response.code);
        if(syncHttpUtil.isHttpOk(response)) {
            setStatus(200);
            setJson(syncHttpUtil.getSucdessMessage(response));
            setMsg("");
        } else {
            setStatus(0);
            setMsg(syncHttpUtil.getErrorMessage(response));
        }

        return this;
    }

    //生成微博需要的json数据
    //picPath的最后一张不需要
    public void makeWeibo(final String context, final List<String> picPath) {
        MakeWeiboBean weiboBean = new MakeWeiboBean();
        MakePicBean picBean = new MakePicBean();
        List<File> picfs = new ArrayList<>();
        List<MakePicBean.PicInfo> picInfos = new ArrayList<>();
        Gson makeGson = new Gson();

        //创建微博内容，并转换为json字符串
        weiboBean.setContent(context);
        weiboBean.setType("公开");
        weiboBean.setUid(ParamConfig.getmUserId());
        String weiboJson = makeGson.toJson(weiboBean);

        //初始化压缩引擎
        TCompress tCompress = new TCompress.Builder()
                .setMaxWidth(1280)
                .setMaxHeight(1280)
                .setQuality(100)
                .setFormat(Bitmap.CompressFormat.JPEG)
                .setConfig(Bitmap.Config.ARGB_8888)
                .build();

        //添加图片信息
        if (picPath.size() == 1) {
            picBean = null;
            picfs = null;
        } else {
            //如果有图片，则添加图片日期
            for(int i=0;i<picPath.size()-1;i++) {

                File compressedFile = tCompress.compressedToFile(new File(picPath.get(i)));
                if (compressedFile == null) {
                    //请查看文件权限问题（其他问题基本不存在，可以查看日志详情）
                    MyLogUtils.d("compress fail: " + picPath.get(i));
                    return;
                } else {
                    picfs.add(compressedFile);
                }

                try {
                    //通过Exif获取照片的拍摄日期
                    ExifInterface exif = new ExifInterface(picPath.get(i));
                    String exif_date = exif.getAttribute(ExifInterface.TAG_DATETIME);
                    //装载数据到bean
                    MakePicBean.PicInfo picInfo = new MakePicBean.PicInfo();
                    if(exif_date == null) {
                        File srcFile = new File(picPath.get(i));
                        srcFile.lastModified();
                        DateFormat df = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
                        picInfo.setCtime(df.format(srcFile.lastModified()));
                    } else {
                        picInfo.setCtime(exif_date);
                    }
                    picInfo.setFname(getFileName(picPath.get(i)));//先装入文件路径
                    picInfos.add(picInfo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //将图片信息转换为json字符串
        String picJson;
        //
        if(picfs == null) {
            picJson = "{\"picInfos\":[]}";
        } else {
            picBean.setPicInfos(picInfos);
            picJson = makeGson.toJson(picBean);
        }

        //装载待上传的post参数
        this.postParams = new HashMap<>();
        this.postParams.put("tokenid",this.tokenid);
        this.postParams.put("weibo",weiboJson);
        if(picJson != null) {
            this.postParams.put("pics", picJson);
        }

        //装载压缩后的文件
        this.postFile = picfs;

    }

    public String getFileName(String pathName) {
        int start = pathName.lastIndexOf("/");
        if (start != -1 ) {
            return pathName.substring(start + 1);
        } else {
            start = pathName.lastIndexOf("\\");
            if(start != -1)
                return pathName.substring(start + 1);
            return null;
        }
    }

}
