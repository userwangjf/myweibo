package com.wangjf.loginin.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wangjf.loginin.R;

public class LogininActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginin);
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, LogininActivity.class);
        //可携带参数
        //intent.putExtra("xxx","xxx");
        return intent;
    }

}
