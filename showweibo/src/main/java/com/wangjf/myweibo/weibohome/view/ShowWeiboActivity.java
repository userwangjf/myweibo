package com.wangjf.myweibo.weibohome.view;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.List;


public class ShowWeiboActivity extends AppCompatActivity {

    RecyclerView        mRecyclerView;
    SimpleRefreshLayout mSimpleRefreshLayout;

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

        mSimpleRefreshLayout.setOnSimpleRefreshListener(new SimpleRefreshLayout.OnSimpleRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mData.add(0, "新加刷新数据");
                        mAdapter.notifyDataSetChanged();
                        mSimpleRefreshLayout.onRefreshComplete();

                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mData.add(mData.size(), "新加加载数据");
                        mAdapter.notifyDataSetChanged();
                        mSimpleRefreshLayout.onLoadMoreComplete();

                        if (mData.size() >= 18) {
                            mSimpleRefreshLayout.showNoMore(true);
                        }
                    }
                }, 1000);
            }
        });

        initData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    List<String> mData = new ArrayList<>();

    private void initData() {
        for (int i = 0; i < 15; i++) {
            mData.add("第" + i + "个");
        }
    }

    private RecyclerView.Adapter mAdapter = new RecyclerView.Adapter() {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(ShowWeiboActivity.this).inflate(R.layout.item_recycler_view, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            TextView tv = (TextView) holder.itemView.findViewById(R.id.tv_text);
            tv.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    };

    private class MyViewHolder extends RecyclerView.ViewHolder {

        TextView  mTextView;
        ImageView mIvImage;

        MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }

}
