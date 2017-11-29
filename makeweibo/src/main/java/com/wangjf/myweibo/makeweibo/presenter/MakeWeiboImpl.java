package com.wangjf.myweibo.makeweibo.presenter;

import android.util.Log;

import com.wangjf.myweibo.makeweibo.bean.MakeWeiboBean;
import com.wangjf.myweibo.makeweibo.model.MakeWeiboModel;
import com.wangjf.myweibo.makeweibo.model.MakeWeiboModelIntf;
import com.wangjf.myweibo.makeweibo.model.OnUploadWeiboListener;
import com.wangjf.myweibo.makeweibo.view.MakeWeiboViewIntf;

import java.io.File;
import java.util.List;


/**
 * Created by wangjf on 17-11-21.
 */

public class MakeWeiboImpl implements MakeWeiboImplIntf,OnUploadWeiboListener {


    private MakeWeiboViewIntf mWeiboView;
    private MakeWeiboModelIntf mWeiboModel;

    public MakeWeiboImpl(MakeWeiboViewIntf viewIntf) {
        mWeiboView = viewIntf;
        mWeiboModel = new MakeWeiboModel();
    }

    @Override
    public void onSuccess(String json) {
        mWeiboView.showOkMsg("创建微搏成功");
        Log.i("WJF","MakeWeibo: " + json);
    }

    @Override
    public void onFailure(String msg) {
        mWeiboView.showFailMsg(msg);
        mWeiboView.hideProgress();
    }

    @Override
    public void addWeibo(String weiboBean, List<File> pics) {
        mWeiboModel.addWeibo(weiboBean,pics,this);
    }
}
