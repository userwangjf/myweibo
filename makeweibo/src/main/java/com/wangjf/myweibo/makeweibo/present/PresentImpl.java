package com.wangjf.myweibo.makeweibo.present;

import android.util.Log;

import com.google.gson.Gson;
import com.wangjf.myutils.MyLogUtils;
import com.wangjf.myweibo.makeweibo.bean.BaseBean;
import com.wangjf.myweibo.makeweibo.model.ModelImpl;
import com.wangjf.myweibo.makeweibo.model.ModelIntf;
import com.wangjf.myweibo.makeweibo.model.OnModelListener;
import com.wangjf.myweibo.makeweibo.view.ViewIntf;

import java.io.File;
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

        MyLogUtils.d("MakeWeibo::onSuccess: " + json);
        Gson gson = new Gson();
        BaseBean baseBean = gson.fromJson(json,BaseBean.class);

        if(baseBean.getRet() == 200) {
            mWeiboView.showOkMsg("创建微搏成功");
        } else if(baseBean.getRet() == 210) {
            mWeiboView.showOkMsg(baseBean.getMsg());
        } else if(baseBean.getRet() == 220) {
            mWeiboView.showFailMsg(baseBean.getMsg());
        }

    }

    @Override
    public void onFailure(String msg) {
        mWeiboView.showFailMsg(msg);
        mWeiboView.hideProgress();
    }

    @Override
    public void addWeibo(String weiboJson, String picJson, List<File> picfs) {
        MyLogUtils.d("makeweibo::addWeibo:weiboJson: " + weiboJson);
        MyLogUtils.d("makeweibo::addWeibo:picJson: " + picJson);
        mWeiboModel.addWeibo(weiboJson,picJson,picfs,this);
    }
}
