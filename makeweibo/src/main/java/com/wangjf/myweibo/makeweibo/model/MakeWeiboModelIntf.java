package com.wangjf.myweibo.makeweibo.model;

import com.wangjf.myweibo.makeweibo.bean.MakeWeiboBean;

import java.io.File;
import java.util.List;

/**
 * Created by wangjf on 17-11-21.
 */

public interface MakeWeiboModelIntf {
    void addWeibo(String weiboJson, String picJson, List<File> picfs, final OnUploadWeiboListener listener);
}
