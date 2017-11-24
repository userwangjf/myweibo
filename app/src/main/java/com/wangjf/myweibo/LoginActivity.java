package com.wangjf.myweibo;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wangjf.myweibo.makeweibo.MakeWeiboActivity;
import com.wangjf.myweibo.weibohome.view.ShowWeiboActivity;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {

    private Intent mStartIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setFullScreen();

        setContentView(R.layout.activity_login);

        /*
        mStartIntent = new Intent(LoginActivity.this, ShowWeiboActivity.class);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendMessage(Message.obtain());
            }
        }, 1000);
        */

    }

    public void onShowWeibo(View v) {
        Toasty.success(LoginActivity.this, "开始浏览微搏", Toast.LENGTH_SHORT, true).show();
        mStartIntent = new Intent(LoginActivity.this, ShowWeiboActivity.class);
        startActivity(mStartIntent);
        finish();
    }

    public void onMakeWeibo(View v) {
        Toasty.success(LoginActivity.this, "开始创作微搏", Toast.LENGTH_SHORT, true).show();
        mStartIntent = new Intent(LoginActivity.this, MakeWeiboActivity.class);
        startActivity(mStartIntent);
        finish();
    }

    private void setFullScreen() {
        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        }
    }

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            startActivity(mStartIntent);
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

}
