package com.wangjf.myweibo.weibohome.presenter;

import android.provider.Settings;
import android.util.Log;

import com.google.gson.Gson;
import com.wangjf.myweibo.weibohome.bean.BaseBean;
import com.wangjf.myweibo.weibohome.bean.ShowWeiboBean;
import com.wangjf.myweibo.weibohome.model.OnLoadWeiboListener;
import com.wangjf.myweibo.weibohome.model.ShowWeiboModel;
import com.wangjf.myweibo.weibohome.model.ShowWeiboModelIntf;
import com.wangjf.myweibo.weibohome.view.ShowWeiboViewIntf;

import java.text.SimpleDateFormat;

/**
 * Created by wangjf on 17-11-21.
 */

public class PresentImpl implements PresentIntf,OnLoadWeiboListener {


    private ShowWeiboViewIntf mWeiboView;
    private ShowWeiboModelIntf mWeiboModel;

    public PresentImpl(ShowWeiboViewIntf viewIntf) {
        mWeiboView = viewIntf;
        mWeiboModel = new ShowWeiboModel();
    }

    @Override
    public void getWeibo() {
        mWeiboView.showProgress();
        mWeiboModel.getWeibo(this);
    }

    @Override
    public void getWeiboMore() {
        mWeiboModel.getWeiboMore(this);
    }

    //处理时间戳
    public String getTimeDiff(String timestemp) {

        int curTS = (int)(System.currentTimeMillis() / 1000);
        int weiboTS = Integer.valueOf(timestemp);
        int diff = curTS - weiboTS;
        String ret;
        if(curTS < weiboTS) {
            ret = "发表于0秒钟前";
        } else if(diff < 60) {
            ret = "发表于" + diff + "秒钟前";
        } else if(diff < 60 * 60) {
            diff = diff / 60;
            ret = "发表于" + diff + "分钟前";
        } else if(diff < 3600 * 24) {
            diff = diff / 60 / 60;
            ret = "发表于" + diff + "小时前";
        } else if(diff < 3600 * 24 * 365) {
            diff = diff / 60 / 60 / 24;
            ret = "发表于" + diff + "天前";
        } else {
            SimpleDateFormat time=new SimpleDateFormat("yyyy年MM月dd日");
            long ts = weiboTS;
            ts *= 1000;
            ret = "发表于" + time.format(ts);
        }

        return ret;
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
            //处理微博的时间
            for(int i=0;i<weiboBean.getData().getWeibo().size();i++) {
                String ts = getTimeDiff(weiboBean.getData().getWeibo().get(i).getTime());
                weiboBean.getData().getWeibo().get(i).setTime(ts);
            }
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
