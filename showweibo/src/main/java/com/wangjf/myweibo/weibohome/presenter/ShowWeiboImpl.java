package com.wangjf.myweibo.weibohome.presenter;

import android.util.Log;

import com.wangjf.myweibo.weibohome.model.OnLoadWeiboListener;
import com.wangjf.myweibo.weibohome.model.ShowWeiboModel;
import com.wangjf.myweibo.weibohome.model.ShowWeiboModelIntf;
import com.wangjf.myweibo.weibohome.view.ShowWeiboViewIntf;

/**
 * Created by wangjf on 17-11-21.
 */

public class ShowWeiboImpl implements ShowWeiboImplIntf,OnLoadWeiboListener {


    private ShowWeiboViewIntf mWeiboView;
    private ShowWeiboModelIntf mWeiboModel;

    public ShowWeiboImpl(ShowWeiboViewIntf viewIntf) {
        mWeiboView = viewIntf;
        mWeiboModel = new ShowWeiboModel();
    }

    @Override
    public void getWeibo() {
        mWeiboModel.getWeibo(this);
    }

    @Override
    public void getWeiboMore() {

    }

    @Override
    public void onSuccess(String json) {
        Log.d("WJF","GET: " + json);
    }

    @Override
    public void onFailure(String msg) {

    }
}
