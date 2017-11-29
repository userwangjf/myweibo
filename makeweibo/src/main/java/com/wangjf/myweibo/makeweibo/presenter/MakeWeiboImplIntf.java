package com.wangjf.myweibo.makeweibo.presenter;

import com.wangjf.myweibo.makeweibo.bean.MakeWeiboBean;

import java.io.File;
import java.util.List;

/**
 * Created by wangjf on 17-11-13.
 */

public interface MakeWeiboImplIntf {
    void addWeibo(String weiboBean, List<File> pics);
}
