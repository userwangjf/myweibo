package com.wangjf.signin.present;


import com.google.gson.Gson;
import com.wangjf.signin.bean.BaseBean;
import com.wangjf.signin.bean.SigninBean;
import com.wangjf.signin.model.ModelImpl;
import com.wangjf.signin.model.ModelIntf;
import com.wangjf.signin.model.OnModelListener;
import com.wangjf.signin.view.ViewIntf;

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
            SigninBean signinBean = gson.fromJson(json,SigninBean.class);
            mViewIntf.showOkMsg(bean.getMsg());
            mViewIntf.UpdateUI(signinBean);
        } else if(bean.getRet() == 220) {
            mViewIntf.showFailMsg(bean.getMsg());
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
