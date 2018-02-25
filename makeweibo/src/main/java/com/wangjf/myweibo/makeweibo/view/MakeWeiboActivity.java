package com.wangjf.myweibo.makeweibo.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.ExifInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.androidadvance.topsnackbar.TSnackbarUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jkt.tcompress.TCompress;
import com.wangjf.myutils.MyLogUtils;
import com.wangjf.myweibo.config.ParamConfig;
import com.wangjf.myweibo.makeweibo.R;
import com.wangjf.myweibo.makeweibo.bean.MakePicBean;
import com.wangjf.myweibo.makeweibo.bean.MakeWeiboBean;
import com.wangjf.myweibo.makeweibo.present.PresentImpl;
import com.wangjf.taskmanager.ExecuteTaskManager;
import com.yongchun.library.view.ImageSelectorActivity;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;


public class MakeWeiboActivity extends AppCompatActivity implements View.OnClickListener,ViewIntf {

    private RecyclerView mRvAddPic;
    private MakeWeiboAdapter mAdapter;
    private List<String> mData = new ArrayList<>();

    private TextView mMakeWeiboSend;
    private TextView mMakeWeiboCancel;
    private EditText mMakeWeiboContext;
    private PresentImpl mPresenter;

    private View    mSnackView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_make_weibo);

        //初始化presenter层
        mPresenter = new PresentImpl(this);

        //snackbar显示view
        mSnackView = findViewById(R.id.id_make_weibo_head);

        //初始化控件
        mMakeWeiboCancel = (TextView)findViewById(R.id.id_make_weibo_cancal);
        mMakeWeiboSend = (TextView)findViewById(R.id.id_make_weibo_send);
        mMakeWeiboContext = (EditText)findViewById(R.id.id_make_weibo_context);
        mMakeWeiboSend.setOnClickListener(this);
        mMakeWeiboCancel.setOnClickListener(this);

        //初始化recyclerview
        mRvAddPic = (RecyclerView) findViewById(R.id.id_rv_add_pic);
        mAdapter = new MakeWeiboAdapter(MakeWeiboActivity.this);
        mRvAddPic.setLayoutManager(new GridLayoutManager(this,3));
        mRvAddPic.setAdapter(mAdapter);
        //添加图片的图标
        mData.add("R.drawable.make_weibo_add_pic");
        mAdapter.notifyDataSetChanged();

        //创建
        createProgressBar();
    }

    //用于启动本activity
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, MakeWeiboActivity.class);
        //可携带参数
        //intent.putExtra("xxx","xxx");
        return intent;
    }

    //在屏幕中间显示progressbar
    private void createProgressBar(){
        //整个Activity布局的最终父布局,参见参考资料
        FrameLayout rootFrameLayout=(FrameLayout) findViewById(android.R.id.content);
        FrameLayout.LayoutParams layoutParams=
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity= Gravity.CENTER;
        mProgressBar=new ProgressBar(this);
        mProgressBar.setLayoutParams(layoutParams);

        rootFrameLayout.addView(mProgressBar);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.id_make_weibo_cancal) {
            finish();
        }
        else if(id == R.id.id_make_weibo_send) {
            MyLogUtils.d("makeweibo: start send weibo");

            if(mMakeWeiboContext.getText().toString().equals("")) {
                TSnackbarUtils.with(mSnackView).setMessage("内容不能为空").showWarning();
                return ;
            }

            //在微博创建成功前，关闭点击功能
            setSendEnable(false);
            TSnackbarUtils.with(mSnackView).setMessage("开始上传微博，请等待").showSuccess();

            String context = mMakeWeiboContext.getText().toString();

            //上传微博
            mPresenter.addWeibo(context,mData);

        }
    }

    private void setSendEnable(boolean enable) {
        if(enable) {
            mMakeWeiboSend.setClickable(true);
            mMakeWeiboSend.setTextColor(Color.rgb(0,0,0));
        } else {
            mMakeWeiboSend.setClickable(false);
            mMakeWeiboSend.setTextColor(Color.rgb(100,100,100));
        }
    }

    //获取用户选择的图片。
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == ImageSelectorActivity.REQUEST_IMAGE){
            ArrayList<String> images = (ArrayList<String>) data.getSerializableExtra(ImageSelectorActivity.REQUEST_OUTPUT);

            mData.addAll(mData.size()-1,images);
            mAdapter.notifyDataSetChanged();

            for(int i=0;i<images.size();i++) {
                MyLogUtils.d("add Image: " + images.get(i));
            }
        }
    }

    @Override
    public void showOkMsg(String msg) {
        TSnackbarUtils.with(mSnackView).setMessage(msg).showSuccess();
        setSendEnable(true);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showFailMsg(String msg) {
        TSnackbarUtils.with(mSnackView).setMessage(msg).showError();
        setSendEnable(true);
    }

    public class MakeWeiboViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mImageAdd;
        ImageView mImageDel;

        public MakeWeiboViewHolder(View itemView) {
            super(itemView);
            mImageAdd = (ImageView) itemView.findViewById(R.id.id_image_add_pic);
            mImageAdd.setOnClickListener(this);
            mImageDel = (ImageView) itemView.findViewById(R.id.id_image_del_pic);
            mImageDel.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            //获取当前click的位置。
            int pos = getAdapterPosition();

            if(view.getId() == R.id.id_image_add_pic)
                onClickImageAdd(pos);
            else if(view.getId() == R.id.id_image_del_pic)
                onClickImageDel(pos);
            else
                Log.i("WJF","click on error");
        }
    }

    //
    public void onClickImageAdd(int pos)
    {
        Log.i("WJF","click on id_image_add_pic at : " + pos);
        ImageSelectorActivity.start(MakeWeiboActivity.this,
                10-mData.size(),
                ImageSelectorActivity.MODE_MULTIPLE,
                true,//ImageSelectorActivity.EXTRA_SHOW_CAMERA,
                true,//ImageSelectorActivity.EXTRA_ENABLE_PREVIEW,
                true//ImageSelectorActivity.EXTRA_ENABLE_CROP
        );

    }

    public void onClickImageDel(int pos)
    {
        Log.i("WJF","click on id_image_del_pic at : " + pos);
        mData.remove(pos);
        mAdapter.notifyDataSetChanged();
    }


    public class MakeWeiboAdapter extends RecyclerView.Adapter<MakeWeiboViewHolder> {

        Context mContext;

        public MakeWeiboAdapter(Context context) {
            mContext = context;
        }

        @Override
        public MakeWeiboViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(mContext).inflate(R.layout.layout_make_weibo_pic, parent, false);
            return new MakeWeiboViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MakeWeiboViewHolder holder, int position) {

            if(mData.get(position).equalsIgnoreCase("R.drawable.make_weibo_add_pic"))
            {
                holder.mImageAdd.setImageResource(R.drawable.make_weibo_add_pic);
                holder.mImageDel.setVisibility(View.INVISIBLE);
            }
            else if(mData.size() > position)
            {
                Glide.with(MakeWeiboActivity.this).load(mData.get(position)).into(holder.mImageAdd);
                holder.mImageDel.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public int getItemCount() {
            if(mData == null)
                return 0;
            else if(mData.size() == 0)
                return 0;
            else if(mData.size() > 9)
                return 9;
            else
                return mData.size();
        }
    }



}
