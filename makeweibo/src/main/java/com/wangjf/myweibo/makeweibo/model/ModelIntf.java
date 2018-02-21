package com.wangjf.myweibo.makeweibo.model;

import java.io.File;
import java.util.List;

/**
 * Created by wangjf on 17-11-21.
 */

public interface ModelIntf {
    void addWeibo(String weiboJson, String picJson, List<File> picfs, final OnModelListener listener);
}
