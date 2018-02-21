package com.wangjf.signin.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wangjf.myweibo.config.ParamConfig;
import com.wangjf.signin.R;
import com.wangjf.signin.bean.SigninBean;
import com.wangjf.signin.present.PresentImpl;
import com.wangjf.signin.present.PresentIntf;
import com.androidadvance.topsnackbar.SnackbarUtils;

import java.util.HashMap;
import java.util.Map;

public class SigninActivity extends AppCompatActivity implements ViewIntf {

    private PresentIntf mPresentIntf;

    private View        mSnackBarView;
    private EditText    mEditAccount;
    private EditText    mEditPasswd;
    private EditText    mEditSigncode;
    private EditText    mEditUsername;
    private Button      mButtonSign;
    private Button      mButtonSignCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signin);

        //加载Present
        mPresentIntf = new PresentImpl(this);

        //提示
        mSnackBarView = findViewById(R.id.signin_layout_title);

        mEditSigncode = (EditText)findViewById(R.id.signin_edit_signcode);
        mEditAccount  = (EditText)findViewById(R.id.signin_edit_account);
        mEditPasswd   = (EditText)findViewById(R.id.signin_edit_passwd);
        mEditUsername = (EditText)findViewById(R.id.signin_edit_username);

        //邀请码按钮是否显示
        mButtonSignCode = (Button)findViewById(R.id.signin_button_signcode);
        if(ParamConfig.getmAdminLogin().equals("1")) {
            mButtonSignCode.setVisibility(View.VISIBLE);
            mButtonSignCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("WJF","SigninActivity::onButtonSigncode,start");
                    Map<String,String> params = new HashMap<>();
                    params.put("requestType","signcode");
                    mPresentIntf.LoadBean(params);
                }
            });
        } else {
            mButtonSignCode.setVisibility(View.GONE);
        }

        //注册按钮
        mButtonSign = (Button)findViewById(R.id.signin_button_signin);
        mButtonSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mSigncode = mEditSigncode.getText().toString();
                String mAccount  = mEditAccount.getText().toString();
                String mPasswd   = mEditPasswd.getText().toString();
                String mUsername = mEditUsername.getText().toString();

                if(mSigncode.isEmpty() || mAccount.isEmpty() || mPasswd.isEmpty() || mUsername.isEmpty()) {
                    SnackbarUtils.with(mSnackBarView).setMessage("邀请码/帐号/密码/昵称 不能为空").show();
                } else {
                    Log.i("WJF","SigninActivity::onButtonSigncode,start");
                    Map<String,String> params = new HashMap<>();
                    params.put("requestType","signin");
                    params.put("signcode",mSigncode);
                    params.put("account",mAccount);
                    params.put("passwd",mPasswd);
                    params.put("username",mUsername);
                    mPresentIntf.LoadBean(params);
                }
            }
        });

    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, SigninActivity.class);
        //可携带参数
        //intent.putExtra("xxx","xxx");
        return intent;
    }

    @Override
    public void UpdateUI(SigninBean bean) {
        mEditSigncode.setText(bean.getData().getValue());
    }

    @Override
    public void showProgress(String msg) {

    }

    @Override
    public void hideProgress(String msg) {

    }

    @Override
    public void showOkMsg(String msg) {
        SnackbarUtils.with(mSnackBarView).setMessage(msg).show();
    }

    @Override
    public void showFailMsg(String msg) {
        Log.i("WJF",msg);
        SnackbarUtils.with(mSnackBarView).setMessage(msg).showWarning();
    }
}
