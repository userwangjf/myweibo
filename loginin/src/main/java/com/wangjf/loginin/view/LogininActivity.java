package com.wangjf.loginin.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wangjf.loginin.R;
import com.wangjf.loginin.bean.Bean;
import com.wangjf.loginin.present.PresentImpl;
import com.wangjf.loginin.present.PresentIntf;

import java.util.HashMap;

public class LogininActivity extends AppCompatActivity implements ViewIntf {

    private PresentIntf mPresentIntf;

    private EditText mEditAccount;
    private EditText mEditPasswd;
    private Button   mLogininSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginin);

        //加载Present
        mPresentIntf = new PresentImpl(this);

        //初始化view
        mEditAccount = (EditText)findViewById(R.id.loginin_user_account);
        mEditPasswd  = (EditText)findViewById(R.id.loginin_user_passwd);
        mLogininSubmit = (Button)findViewById(R.id.loginin_submit);

        mLogininSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> params;
                String mUserAccount = mEditAccount.getText().toString();
                String mUserPasswd = mEditPasswd.getText().toString();
                Log.i("WJF","Login: " + mUserAccount + "+" + mUserPasswd);
                mPresentIntf.LoadBean(null);
            }
        });


    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, LogininActivity.class);
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
