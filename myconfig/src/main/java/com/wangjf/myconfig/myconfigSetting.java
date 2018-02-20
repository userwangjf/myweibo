package com.wangjf.myconfig;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wangjf.editdialog.EditDialog;
import com.wangjf.loginin.view.LogininActivity;

public class myconfigSetting extends AppCompatActivity {


    private RelativeLayout mListLoginin;

    private RelativeLayout mListServerAddr;
    private TextView      mTvServerAddr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_myconfig_activity);

        //登录
        mListLoginin = (RelativeLayout)findViewById(R.id.myconfig_list_loginin);
        mListLoginin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = LogininActivity.newIntent(myconfigSetting.this);
                startActivity(intent);
            }
        });

        //显示服务器地址
        mTvServerAddr = (TextView)findViewById(R.id.myconfig_tv_server);


        //服务器设置
        mListServerAddr = (RelativeLayout)findViewById(R.id.mysetting_server_addr);
        mListServerAddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditDialog editDialog = new EditDialog(myconfigSetting.this, mTvServerAddr.getText().toString());
                editDialog.show();
                editDialog.setOnPosNegClickListener(new EditDialog.OnPosNegClickListener() {
                    @Override
                    public void posClickListener(String value) {
                        mTvServerAddr.setText(value);

                    }

                    @Override
                    public void negCliclListener(String value) {

                    }
                });
            }
        });





    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, myconfigSetting.class);
        //可携带参数
        //intent.putExtra("xxx","xxx");
        return intent;
    }
}
