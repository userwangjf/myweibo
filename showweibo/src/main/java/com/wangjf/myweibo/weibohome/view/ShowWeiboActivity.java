package com.wangjf.myweibo.weibohome.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dengzq.simplerefreshlayout.SimpleBottomView;
import com.dengzq.simplerefreshlayout.SimpleLoadView;
import com.dengzq.simplerefreshlayout.SimpleRefreshLayout;
import com.dengzq.simplerefreshlayout.SimpleRefreshView;
import com.wangjf.myweibo.weibohome.R;
import com.wangjf.myweibo.weibohome.bean.ShowWeiboBean;
import com.wangjf.myweibo.weibohome.presenter.ShowWeiboImpl;
import com.wangjf.myweibo.weibohome.presenter.ShowWeiboImplIntf;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;


public class ShowWeiboActivity extends AppCompatActivity implements SimpleRefreshLayout.OnSimpleRefreshListener,ShowWeiboViewIntf {

    RecyclerView        mRecyclerView;
    SimpleRefreshLayout mSimpleRefreshLayout;
    ShowWeiboAdapter    mAdapter;
    private ShowWeiboImpl mPresenter;

    List<ShowWeiboBean.DataBean.WeiboBean> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo_home);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mSimpleRefreshLayout = (SimpleRefreshLayout) findViewById(R.id.simple_refresh);

        mSimpleRefreshLayout.setScrollEnable(true);
        mSimpleRefreshLayout.setPullUpEnable(true);
        mSimpleRefreshLayout.setPullDownEnable(true);
        mSimpleRefreshLayout.setHeaderView(new SimpleRefreshView(this));
        mSimpleRefreshLayout.setFooterView(new SimpleLoadView(this));
        mSimpleRefreshLayout.setBottomView(new SimpleBottomView(this));

        mSimpleRefreshLayout.setOnSimpleRefreshListener(this);

        mAdapter = new ShowWeiboAdapter(ShowWeiboActivity.this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mPresenter = new ShowWeiboImpl(this);
    }

    public class ShowWeiboViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        public ShowWeiboViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.id_sweibo_context);
        }
    }

    public class ShowWeiboAdapter extends RecyclerView.Adapter<ShowWeiboViewHolder> {

        Context mContext;

        public ShowWeiboAdapter(Context context) {
            mContext = context;
        }

        @Override
        public ShowWeiboViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(mContext).inflate(R.layout.layout_weibo_item, parent, false);
            return new ShowWeiboViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ShowWeiboViewHolder holder, int position) {
            //设置数据
            holder.mTextView.setText(mData.get(position).getContent());
            //Log.i("WJF","pos:" + position);
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

    }

    @Override
    public void hideProgress() {
        mSimpleRefreshLayout.onRefreshComplete();
        mSimpleRefreshLayout.onLoadMoreComplete();
    }

    @Override
    public void showFailMsg(String msg) {
        Toasty.warning(ShowWeiboActivity.this,msg,Toast.LENGTH_SHORT,true).show();
    }
}
