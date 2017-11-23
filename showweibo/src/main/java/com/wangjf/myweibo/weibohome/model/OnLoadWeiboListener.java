package com.wangjf.myweibo.weibohome.model;

import com.wangjf.myweibo.weibohome.bean.ShowWeiboBean;

/**
 * Created by wangjf on 17-11-23.
 */

public interface OnLoadWeiboListener {

    void onSuccess(int type, String json);

    void onFailure(String msg);

}
