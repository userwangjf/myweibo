package com.wangjf.myweibo.weibohome.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.wangjf.myweibo.weibohome.bean.BaseBean;
import com.wangjf.myweibo.weibohome.bean.ShowWeiboBean;
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
        mWeiboModel.getWeiboMore(this);
    }

    @Override
    public void onSuccess(int type, String json) {
        Log.d("WJF","GET: " + json);
        mWeiboView.hideProgress();

        Gson weiboGson = new Gson();
        BaseBean retBean = weiboGson.fromJson(json,BaseBean.class);

        if(retBean.getRet() == 200)
        {
            ShowWeiboBean weiboBean = weiboGson.fromJson(json, ShowWeiboBean.class);
            if(type == 1)
                mWeiboView.refreshWeibo(weiboBean.getData().getWeibo());
            else
                mWeiboView.addWeibo(weiboBean.getData().getWeibo());

        }
        else
            mWeiboView.showFailMsg(retBean.getMsg());
    }

    @Override
    public void onFailure(String msg) {
        mWeiboView.showFailMsg(msg);
        mWeiboView.hideProgress();
    }
}
