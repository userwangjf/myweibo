package com.wangjf.MVP.view;

import com.wangjf.MVP.bean.Bean;


/**
 * Created by wangjf on 17-11-13.
 */

public interface ViewIntf {

    //更新UI
    void UpdateUI(Bean bean);

    //显示等待标记
    void showProgress(String msg);
    //隐藏等待标记
    void hideProgress(String msg);

    //处理结束后，显示相关信息
    void showOkMsg(String msg);
    void showFailMsg(String msg);
}

