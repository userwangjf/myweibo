package com.wangjf.loginin.present;


import com.google.gson.Gson;
import com.wangjf.loginin.bean.BaseBean;
import com.wangjf.loginin.bean.LogininBean;
import com.wangjf.loginin.model.ModelImpl;
import com.wangjf.loginin.model.ModelIntf;
import com.wangjf.loginin.model.OnModelListener;
import com.wangjf.loginin.view.ViewIntf;
import com.wangjf.myweibo.config.ParamConfig;

import java.util.Map;

/**
 * Created by wangjf on 17-11-21.
 */

public class PresentImpl implements PresentIntf,OnModelListener {

    private ModelIntf mModelIntf;
    private ViewIntf mViewIntf;


    public PresentImpl(ViewIntf viewIntf) {
        mModelIntf = new ModelImpl();
        mViewIntf = viewIntf;
    }

    @Override
    public void onSuccess(String json) {
        Gson gson = new Gson();
        BaseBean bean = gson.fromJson(json,BaseBean.class);
        if(bean.getRet() == 200) {
            LogininBean logininBean = gson.fromJson(json,LogininBean.class);
            String tokenid = logininBean.getData().getTokenid();
            ParamConfig.setTokenid(tokenid);
            mViewIntf.showOkMsg("登录成功: " + tokenid);
        } else if(bean.getRet() == 220) {
            mViewIntf.showFailMsg("登录失败：" + bean.getMsg());
        }

    }

    @Override
    public void onFailure(String msg) {
        mViewIntf.showFailMsg(msg);
    }

    @Override
    public void LoadBean(Map<String, String> params) {
        mModelIntf.LoadBean(this,params);
    }
}
