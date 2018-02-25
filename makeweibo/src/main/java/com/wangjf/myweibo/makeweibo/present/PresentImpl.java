package com.wangjf.myweibo.makeweibo.present;

import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.util.Log;

import com.google.gson.Gson;
import com.jkt.tcompress.TCompress;
import com.wangjf.myutils.MyLogUtils;
import com.wangjf.myweibo.config.ParamConfig;
import com.wangjf.myweibo.makeweibo.bean.BaseBean;
import com.wangjf.myweibo.makeweibo.bean.MakePicBean;
import com.wangjf.myweibo.makeweibo.bean.MakeWeiboBean;
import com.wangjf.myweibo.makeweibo.model.ModelImpl;
import com.wangjf.myweibo.makeweibo.model.ModelIntf;
import com.wangjf.myweibo.makeweibo.model.OnModelListener;
import com.wangjf.myweibo.makeweibo.view.ViewIntf;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by wangjf on 17-11-21.
 */

public class PresentImpl implements PresentIntf,OnModelListener {


    private ViewIntf mWeiboView;
    private ModelIntf mWeiboModel;

    public PresentImpl(ViewIntf viewIntf) {
        mWeiboView = viewIntf;
        mWeiboModel = new ModelImpl();
    }

    @Override
    public void onSuccess(String json) {

        MyLogUtils.d("get json: " + json);
        Gson gson = new Gson();
        BaseBean baseBean = gson.fromJson(json,BaseBean.class);

        if(baseBean.getRet() == 200) {
            mWeiboView.showOkMsg("创建微搏成功");
        } else if(baseBean.getRet() == 210) {
            mWeiboView.showOkMsg(baseBean.getMsg());
        } else if(baseBean.getRet() == 220) {
            mWeiboView.showFailMsg(baseBean.getMsg());
        }

        mWeiboView.hideProgress();

    }

    @Override
    public void onFailure(String msg) {
        mWeiboView.showFailMsg(msg);
        mWeiboView.hideProgress();
    }

    @Override
    public void addWeibo(String weiboContext, List<String> picPath) {
        mWeiboModel.addWeibo(weiboContext,picPath,this);
        mWeiboView.showProgress();
    }

}
