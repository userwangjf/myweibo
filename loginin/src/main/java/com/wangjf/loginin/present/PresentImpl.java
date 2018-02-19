package com.wangjf.loginin.present;


import com.wangjf.loginin.model.ModelImpl;
import com.wangjf.loginin.model.ModelIntf;
import com.wangjf.loginin.model.OnModelListener;
import com.wangjf.loginin.view.ViewIntf;

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
        mViewIntf.showOkMsg(json);
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
