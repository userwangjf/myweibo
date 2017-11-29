package com.wangjf.myweibo.makeweibo.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.google.gson.Gson;
import com.wangjf.myweibo.makeweibo.R;
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

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.id_make_weibo_cancal) {
            finish();
        }
        else if(id == R.id.id_make_weibo_send) {
            Log.i("WJF","start send weibo");
            onClickSend();
        }
    }

    public void onClickSend() {
        MakeWeiboBean weiboData = new MakeWeiboBean();
        List<File> pics = new ArrayList<>();
        List<MakeWeiboBean.PicBean> pic_list = new ArrayList<>();
        weiboData.setContent(mMakeWeiboContext.getText().toString());
        weiboData.setType("公开");
        if (mData.size() == 1) {
            pics = null;
            pic_list = null;
        }
        else {
            //如果有图片，则添加图片日期

            for(int i=0;i<mData.size()-1;i++) {
                pics.add(new File(mData.get(i)));
                try {
                    ExifInterface exif = new ExifInterface(mData.get(i));
                    String exif_date = exif.getAttribute(ExifInterface.TAG_DATETIME);
                    MakeWeiboBean.PicBean picbean = new MakeWeiboBean.PicBean();
                    picbean.setCtime(exif_date);
                    picbean.setUrl("pics");
                    pic_list.add(picbean);
                    Log.i("WJF", "file time: " + exif_date);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        weiboData.setPic(pic_list);
        Gson makeGson = new Gson();
        String weiboJson = makeGson.toJson(weiboData);
        Log.i("WJF",weiboJson);

        mPresenter.addWeibo(weiboJson,pics);
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
        Toasty.info(this,msg);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showFailMsg(String msg) {
        Toasty.warning(this,msg);
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
                onClockImageDel(pos);
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

    public void onClockImageDel(int pos)
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
                Bitmap bmp= BitmapFactory.decodeFile(mData.get(position));
                holder.mImageAdd.setImageBitmap(bmp);
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
