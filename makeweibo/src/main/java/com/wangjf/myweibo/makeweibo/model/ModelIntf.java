package com.wangjf.myweibo.makeweibo.model;

import java.io.File;
import java.util.List;

/**
 * Created by wangjf on 17-11-21.
 */

public interface ModelIntf {
    void addWeibo(String weiboContext, List<String> picPath, final OnModelListener listener);
}
