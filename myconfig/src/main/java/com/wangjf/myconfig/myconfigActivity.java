package com.wangjf.myconfig;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.wangjf.loginin.view.LogininActivity;

public class myconfigActivity extends AppCompatActivity {


    private RelativeLayout mListLoginin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_myconfig_activity);

        //登录
        mListLoginin = (RelativeLayout)findViewById(R.id.myconfig_list_loginin);
        mListLoginin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = LogininActivity.newIntent(myconfigActivity.this);
                startActivity(intent);
            }
        });


    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, myconfigActivity.class);
        //可携带参数
        //intent.putExtra("xxx","xxx");
        return intent;
    }
}
