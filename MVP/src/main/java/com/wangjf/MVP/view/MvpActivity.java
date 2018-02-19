package com.wangjf.MVP.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wangjf.loginin.R;
import com.wangjf.MVP.bean.Bean;
import com.wangjf.MVP.present.PresentImpl;
import com.wangjf.MVP.present.PresentIntf;

public class MvpActivity extends AppCompatActivity implements ViewIntf {

    private PresentIntf mPresentIntf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginin);

        //加载Present
        mPresentIntf = new PresentImpl(this);
        mPresentIntf.LoadBean(null);
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, MvpActivity.class);
        //可携带参数
        //intent.putExtra("xxx","xxx");
        return intent;
    }

    @Override
    public void UpdateUI(Bean bean) {

    }

    @Override
    public void showProgress(String msg) {

    }

    @Override
    public void hideProgress(String msg) {

    }

    @Override
    public void showOkMsg(String msg) {

    }

    @Override
    public void showFailMsg(String msg) {

    }
}
