package com.wangjf.myconfig;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wangjf.editdialog.EditDialog;
import com.wangjf.loginin.view.LogininActivity;
import com.wangjf.myutils.SharedPreferencesUtils;
import com.wangjf.myweibo.config.ParamConfig;
import com.wangjf.promptdialog.PromptDialog;

public class AppSetting extends AppCompatActivity {


    private static RelativeLayout   mListLoginin;
    private static RelativeLayout   mListServerAddr;
    private static TextView         mTvServerAddr;
    private static RelativeLayout   mListSetting;
    private static TextView         mLoginText;

    final static int CODE_LOGININ   = 0x1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_myconfig_activity);

        //登录
        mListLoginin = (RelativeLayout)findViewById(R.id.myconfig_list_loginin);
        mLoginText = (TextView)findViewById(R.id.loginin_loginin_text);
        if(ParamConfig.getTokenid() != null) {
            mLoginText.setText("已登录");
        }
        mListLoginin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = LogininActivity.newIntent(AppSetting.this);
                startActivityForResult(intent, CODE_LOGININ);
            }
        });

        //显示服务器地址
        mTvServerAddr = (TextView)findViewById(R.id.myconfig_tv_server);
        mTvServerAddr.setText(ParamConfig.getUrlHost());

        //服务器设置
        mListServerAddr = (RelativeLayout)findViewById(R.id.mysetting_server_addr);
        mListServerAddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditDialog editDialog = new EditDialog(AppSetting.this, mTvServerAddr.getText().toString());
                editDialog.show();
                editDialog.setOnPosNegClickListener(new EditDialog.OnPosNegClickListener() {
                    @Override
                    public void posClickListener(String value) {
                        mTvServerAddr.setText(value);
                        ParamConfig.setUrlHost(value);
                    }

                    @Override
                    public void negCliclListener(String value) {

                    }
                });
            }
        });


        mListSetting = (RelativeLayout)findViewById(R.id.myconfig_list_setting);
        mListSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PromptDialog promptDialog = new PromptDialog(AppSetting.this, "开发中");
                promptDialog.show();
                promptDialog.setOnPosNegClickListener(new PromptDialog.OnPosNegClickListener() {
                    @Override
                    public void posClickListener(String value) {

                    }

                    @Override
                    public void negCliclListener(String value) {

                    }
                });
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i("WJF","AppSetting onActivityResult," + requestCode + "," + resultCode);

        if(RESULT_CANCELED == resultCode) {
            return ;
        }

        if(requestCode == CODE_LOGININ) {
            boolean loginin_state = data.getBooleanExtra("loginin_state",false);
            if(loginin_state) {
                mLoginText.setText("登录成功");
            }
        }


    }

    public static String getServerAddr() {
        return mTvServerAddr.getText().toString();
    }


    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, AppSetting.class);
        //可携带参数
        //intent.putExtra("xxx","xxx");
        return intent;
    }
}
