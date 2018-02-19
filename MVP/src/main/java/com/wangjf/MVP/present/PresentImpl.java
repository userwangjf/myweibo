package com.wangjf.MVP.present;


import com.wangjf.MVP.view.ViewIntf;
import com.wangjf.MVP.model.ModelImpl;
import com.wangjf.MVP.model.ModelIntf;
import com.wangjf.MVP.model.OnModelListener;

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
        mViewIntf.UpdateUI(null);
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
