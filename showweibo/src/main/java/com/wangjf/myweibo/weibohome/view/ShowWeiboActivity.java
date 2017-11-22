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
import com.wangjf.myweibo.weibohome.presenter.ShowWeiboImplIntf;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;


public class ShowWeiboActivity extends AppCompatActivity implements SimpleRefreshLayout.OnSimpleRefreshListener,ShowWeiboViewIntf {

    RecyclerView        mRecyclerView;
    SimpleRefreshLayout mSimpleRefreshLayout;
    ShowWeiboAdapter    mAdapter;

    List<ShowWeiboBean> mData = new ArrayList<>();

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
        initData();
    }


    private void initData() {

        for (int i = 0; i < 15; i++) {
            ShowWeiboBean bean = new ShowWeiboBean();
            bean.setMsg("haha " + i);
            mData.add(bean);
            Log.i("WJF","init data" + mData.get(i).getMsg());
        }
        for(int i=0;i<15;i++){
            Log.i("WJF","init data" + mData.get(i).getMsg());
        }
    }

    @Override
    public void onRefresh() {


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Log.i("WJF","ShowWeibo onRefresh");
                ShowWeiboBean bean = new ShowWeiboBean();
                bean.setMsg("insert");
                mData.add(0,bean);
                mAdapter.notifyDataSetChanged();
                mSimpleRefreshLayout.onRefreshComplete();

            }
        }, 50);

    }

    @Override
    public void onLoadMore() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                ShowWeiboBean bean = new ShowWeiboBean();
                bean.setMsg("append");
                mData.add(bean);
                mAdapter.notifyDataSetChanged();
                mSimpleRefreshLayout.onLoadMoreComplete();

            }
        }, 50);

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
            holder.mTextView.setText(mData.get(position).getMsg());
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
    public void addWeibo(List<ShowWeiboBean> weibo) {
        mData = weibo;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showFailMsg(String msg) {
        Toasty.warning(ShowWeiboActivity.this,msg,Toast.LENGTH_SHORT,true).show();
    }
}
