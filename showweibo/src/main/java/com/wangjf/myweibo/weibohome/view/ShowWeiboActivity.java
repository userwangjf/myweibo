package com.wangjf.myweibo.weibohome.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dengzq.simplerefreshlayout.SimpleBottomView;
import com.dengzq.simplerefreshlayout.SimpleLoadView;
import com.dengzq.simplerefreshlayout.SimpleRefreshLayout;
import com.dengzq.simplerefreshlayout.SimpleRefreshView;
import com.wangjf.MultImageView.MultImageView;
import com.wangjf.myconfig.AppSetting;
import com.wangjf.myweibo.config.ParamConfig;
import com.wangjf.myweibo.makeweibo.view.MakeWeiboActivity;
import com.wangjf.myweibo.weibohome.R;
import com.wangjf.myweibo.weibohome.bean.ShowWeiboBean;
import com.wangjf.myweibo.weibohome.presenter.PresentImpl;
import com.wc.dragphoto.widget.ImageShowActivity;


import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;


public class ShowWeiboActivity extends AppCompatActivity implements SimpleRefreshLayout.OnSimpleRefreshListener,ShowWeiboViewIntf {

    RecyclerView        mRecyclerView;
    SimpleRefreshLayout mSimpleRefreshLayout;
    ShowWeiboAdapter    mAdapter;
    private PresentImpl mPresenter;
    private RelativeLayout mBarHome, mBarMessage, mBarDiscovery, mBarProfile;
    private ImageView mBarMakeWeibo;
    List<ShowWeiboBean.DataBean.WeiboBean> mData = new ArrayList<>();
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_show_weibo_home);


        //下拉刷新初始化
        mSimpleRefreshLayout = (SimpleRefreshLayout) findViewById(R.id.simple_refresh);
        mSimpleRefreshLayout.setScrollEnable(true);
        mSimpleRefreshLayout.setPullUpEnable(true);
        mSimpleRefreshLayout.setPullDownEnable(true);
        mSimpleRefreshLayout.setHeaderView(new SimpleRefreshView(this));
        mSimpleRefreshLayout.setFooterView(new SimpleLoadView(this));
        mSimpleRefreshLayout.setBottomView(new SimpleBottomView(this));
        mSimpleRefreshLayout.setOnSimpleRefreshListener(this);

        //微博列表
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new ShowWeiboAdapter(ShowWeiboActivity.this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        //Home按钮
        mBarHome = (RelativeLayout) findViewById(R.id.weibo_bottombar_home);
        mBarHome.setSelected(true);

        //新建微搏按钮
        mBarMakeWeibo = (ImageView) findViewById(R.id.id_bar_make_weibo);
        mBarMakeWeibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MakeWeiboActivity.newIntent(ShowWeiboActivity.this);
                startActivity(intent);
            }
        });

        //myConfig按钮
        mBarProfile = (RelativeLayout)findViewById(R.id.weibo_bottombar_profile_layout);
        mBarProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AppSetting.newIntent(ShowWeiboActivity.this);
                startActivity(intent);
            }
        });


        mPresenter = new PresentImpl(this);

        createProgressBar();
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


    public class ShowWeiboViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;
        MultImageView mMultImage;
        TextView mProfileName;
        TextView mProfileTime;

        public ShowWeiboViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.id_sweibo_context);
            mMultImage = (MultImageView) itemView.findViewById(R.id.id_sweibo_image);
            mProfileName = (TextView) itemView.findViewById(R.id.profile_name);
            mProfileTime = (TextView)itemView.findViewById(R.id.profile_time);
        }
    }

    public class ShowWeiboAdapter extends RecyclerView.Adapter<ShowWeiboViewHolder> {

        Context mContext;
        int max_width;

        public ShowWeiboAdapter(Context context) {
            mContext = context;
        }

        @Override
        public ShowWeiboViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(mContext).inflate(R.layout.layout_weibo_item, parent, false);

            return new ShowWeiboViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ShowWeiboViewHolder holder, int position) {
            //设置数据
            //Log.i("WJF","pos:" + position);
            if(mData == null)
                return;
            else if(mData.size() > position) {
                holder.mTextView.setText(mData.get(position).getContent());
                holder.mProfileName.setText(mData.get(position).getUsername());
                holder.mProfileTime.setText(mData.get(position).getTime());

                if(mData.get(position).getPic() != null) {
                    //防止显示错乱
                    holder.mMultImage.resetView();
                    List<String> imgs = new ArrayList<>();
                    //添加数据
                    for(int i=0;i<mData.get(position).getPic().size();i++){
                        String UrlPic = ParamConfig.getUrlHost() + mData.get(position).getPic().get(i).getUrl();
                        Log.i("WJF",UrlPic);
                        imgs.add(UrlPic);
                    }
                    holder.mMultImage.setList(imgs);

                    //设置click事件
                    holder.mMultImage.setOnItemClickListener(new MultImageView.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Log.d("WJF", "click: " + position);
                            //list转数组
                            final String[] imgPath = holder.mMultImage.getImgPathList().toArray(new String[0]);
                            final ImageView[] imgView = holder.mMultImage.getImgViewList().toArray(new ImageView[0]);
                            if(imgView.length == 0) return;
                            if(imgPath.length == 0) return;
                            ImageShowActivity.startImageActivity((Activity) mContext, imgView,imgPath, position);
                        }
                    });
                }
            }

        }

        @Override
        public int getItemCount() {
            if(mData == null)
                return 0;
            else
                return mData.size();
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.getWeibo();
    }

    @Override
    public void onLoadMore() {
        mPresenter.getWeiboMore();
    }

    @Override
    public void addWeibo(List<ShowWeiboBean.DataBean.WeiboBean> weibo) {

        mData.clear();
        for(int i=0;i<weibo.size();i++) {
            mData.add(weibo.get(i));
        }

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshWeibo(List<ShowWeiboBean.DataBean.WeiboBean> weibo) {
        for(int i=0;i<weibo.size();i++) {
            mData.add(weibo.get(i));
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
        mSimpleRefreshLayout.onRefreshComplete();
        mSimpleRefreshLayout.onLoadMoreComplete();
    }

    @Override
    public void showFailMsg(String msg) {
        Toasty.warning(ShowWeiboActivity.this,msg,Toast.LENGTH_SHORT,true).show();
    }

}
