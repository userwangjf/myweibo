package com.wangjf.myweibo.makeweibo.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.ExifInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jkt.tcompress.TCompress;
import com.wangjf.myweibo.makeweibo.R;
import com.wangjf.myweibo.makeweibo.bean.MakePicBean;
import com.wangjf.myweibo.makeweibo.bean.MakeWeiboBean;
import com.wangjf.myweibo.makeweibo.presenter.MakeWeiboImpl;
import com.yongchun.library.view.ImageSelectorActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;


public class MakeWeiboActivity extends AppCompatActivity implements View.OnClickListener,MakeWeiboViewIntf {

    private RecyclerView mRvAddPic;
    private MakeWeiboAdapter mAdapter;
    private List<String> mData = new ArrayList<>();

    private TextView mMakeWeiboSend;
    private TextView mMakeWeiboCancel;
    private EditText mMakeWeiboContext;
    private MakeWeiboImpl mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_make_weibo);

        //初始化presenter层
        mPresenter = new MakeWeiboImpl(this);

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
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, MakeWeiboActivity.class);
        //可携带参数
        //intent.putExtra("xxx","xxx");
        return intent;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.id_make_weibo_cancal) {
            finish();
        }
        else if(id == R.id.id_make_weibo_send) {
            Log.i("WJF","start send weibo");

            //在微博创建成功前，关闭点击功能
            mMakeWeiboSend.setClickable(false);
            mMakeWeiboSend.setTextColor(Color.rgb(200,200,200));
            Toasty.info(this,"开始上传微博，请等待...").show();
            onClickSend();
        }
    }

    public void onClickSend() {
        MakeWeiboBean weiboBean = new MakeWeiboBean();
        MakePicBean picBean = new MakePicBean();
        List<File> picfs = new ArrayList<>();
        List<MakePicBean.PicInfo> picInfos = new ArrayList<>();
        Gson makeGson = new Gson();

        //创建微博内容，并转换为json字符串
        weiboBean.setContent(mMakeWeiboContext.getText().toString());
        weiboBean.setType("公开");
        String weiboJson = makeGson.toJson(weiboBean);

        //初始化压缩引擎
        TCompress tCompress = new TCompress.Builder()
                .setMaxWidth(1080)
                .setMaxHeight(1080)
                .setQuality(100)
                .setFormat(Bitmap.CompressFormat.JPEG)
                .setConfig(Bitmap.Config.RGB_565)
                .build();

        //添加图片信息
        if (mData.size() == 1) {
            picBean = null;
            picfs = null;
        } else {
            //如果有图片，则添加图片日期
            for(int i=0;i<mData.size()-1;i++) {

                File compressedFile = tCompress.compressedToFile(new File(mData.get(i)));
                if (compressedFile == null) {
                    //请查看文件权限问题（其他问题基本不存在，可以查看日志详情）
                    Log.i("WJF","压缩失败");
                    return;
                } else {
                    picfs.add(compressedFile);
                }

                try {
                    //通过Exif获取照片的拍摄日期
                    ExifInterface exif = new ExifInterface(mData.get(i));
                    String exif_date = exif.getAttribute(ExifInterface.TAG_DATETIME);
                    //装载数据到bean
                    MakePicBean.PicInfo picInfo = new MakePicBean.PicInfo();
                    picInfo.setCtime(exif_date);
                    picInfo.setFname(getFileName(mData.get(i)));//先装入文件路径
                    picInfos.add(picInfo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //将图片信息转换为json字符串
        String picJson;
        //
        if(picfs == null) {
            picJson = "{\"picInfos\":[]}";
        } else {
            picBean.setPicInfos(picInfos);
            picJson = makeGson.toJson(picBean);
        }
        Log.i("WJF","make weiboJson : " + weiboJson);
        Log.i("WJF","make picJson : " + picJson);

        //发送到服务器
        mPresenter.addWeibo(weiboJson,picJson,picfs);
    }

    public void initCompress() {

    }

    public String getFileName(String pathName) {
        int start = pathName.lastIndexOf("/");
        if (start != -1 ) {
            return pathName.substring(start + 1);
        } else {
            start = pathName.lastIndexOf("\\");
            if(start != -1)
                return pathName.substring(start + 1);
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == ImageSelectorActivity.REQUEST_IMAGE){
            ArrayList<String> images = (ArrayList<String>) data.getSerializableExtra(ImageSelectorActivity.REQUEST_OUTPUT);

            mData.addAll(mData.size()-1,images);
            mAdapter.notifyDataSetChanged();

            for(int i=0;i<images.size();i++) {
                Log.i("WJF","add Image: " + images.get(i));
            }
        }
    }

    @Override
    public void showOkMsg(String msg) {
        Toasty.info(this,msg).show();
        mMakeWeiboSend.setClickable(true);
        mMakeWeiboSend.setTextColor(Color.rgb(0,0,0));
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showFailMsg(String msg) {
        Toasty.warning(this,msg).show();
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
