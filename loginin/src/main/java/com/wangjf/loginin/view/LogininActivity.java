package com.wangjf.loginin.view;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wangjf.loginin.R;
import com.wangjf.loginin.bean.BaseBean;
import com.wangjf.loginin.present.PresentImpl;
import com.wangjf.loginin.present.PresentIntf;
import com.androidadvance.topsnackbar.SnackbarUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class LogininActivity extends AppCompatActivity implements ViewIntf {

    final static int CODE_LOGININ   = 0x1000;

    private PresentIntf mPresentIntf;

    private View        mSnackBarView;
    private EditText mEditAccount;
    private EditText mEditPasswd;
    private Button   mLogininSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_loginin);

        //加载Present
        mPresentIntf = new PresentImpl(this);

        mSnackBarView = findViewById(R.id.loginin_layout_base);

        //初始化view
        mEditAccount = (EditText)findViewById(R.id.loginin_user_account);
        mEditPasswd  = (EditText)findViewById(R.id.loginin_user_passwd);
        mLogininSubmit = (Button)findViewById(R.id.loginin_submit);

        mLogininSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mUserAccount = mEditAccount.getText().toString();
                String mUserPasswd = mEditPasswd.getText().toString();
                if(mUserAccount.isEmpty() || mUserPasswd.isEmpty()) {
                    SnackbarUtils.with(mSnackBarView).setMessage("帐号/密码不能为空").show();
                } else {
                    Map<String, String> params = new HashMap<>();
                    Log.i("WJF", "LogininActivity::LoginSubmit," + mUserAccount);
                    params.put("account", mUserAccount);
                    params.put("passwd", mUserPasswd);
                    Log.i("WJF","params: " + params.get("account"));
                    mPresentIntf.LoadBean(params);
                }
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
    public void UpdateUI(BaseBean bean) {

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

        //登录成功，自动退出
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendMessage(Message.obtain());
            }
        }, 1000);
    }

    @Override
    public void showFailMsg(String msg) {
        Log.i("WJF",msg);
    }

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.i("WJF","LoginIn 成功");
            Intent intentRet = new Intent();
            intentRet.putExtra("loginin_state",true);
            setResult(RESULT_OK,intentRet);
            finish();
        }
    };
}
