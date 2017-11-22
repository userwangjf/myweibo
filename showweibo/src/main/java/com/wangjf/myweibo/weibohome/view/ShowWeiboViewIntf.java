package com.wangjf.myweibo.weibohome.view;

import com.wangjf.myweibo.weibohome.bean.ShowWeiboBean;

import java.util.List;

/**
 * Created by wangjf on 17-11-13.
 */

public interface ShowWeiboViewIntf {

    void addWeibo(List<ShowWeiboBean> weibo);

    void showProgress();
    void hideProgress();

    void showFailMsg(String msg);
}

