package com.wangjf.myweibo.makeweibo.view;

import com.wangjf.myweibo.makeweibo.bean.MakeWeiboBean;

import java.io.File;
import java.util.List;

/**
 * Created by wangjf on 17-11-13.
 */

public interface MakeWeiboViewIntf {

    void showProgress();
    void hideProgress();

    void showFailMsg(String msg);
    void showOkMsg(String msg);
}

