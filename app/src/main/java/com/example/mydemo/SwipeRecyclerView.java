package com.example.mydemo;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.autolayout.AutoFrameLayout;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

public class SwipeRecyclerView extends AutoFrameLayout {


    private RecyclerView mRecycleView;
    private SwipeRefreshLayout mSwipeLayout;
    private boolean isLoadMore = true;
    private boolean isAutoLoadMore = true;
    private HeaderAndFooderAdapter mAdapter;
    private View header = null;
    private View footer = null;

    public SwipeRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.swipe_recyclerview, this, true);
        mRecycleView = (RecyclerView) findViewById(R.id.recycler_view);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
//        mSwipeLayout.setColorSchemeResources(); 加载 的旋转颜色
    }

    public SwipeRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SwipeRecyclerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public RecyclerView getLayoutManager() {
        return mRecycleView;
    }


    public void setAdapter(RecyclerView.Adapter adapter) {
        mAdapter = new HeaderAndFooderAdapter(adapter);
        if (header != null) {
            mAdapter.addHeaderView(header);
        }
        if (null != footer) {
            mAdapter.addFooterView(footer);
        }
        mRecycleView.setAdapter(mAdapter);

    }

    /**
     * 添加头部 视图
     *
     * @param header
     */
    public void addHeaderView(View header) {
        this.header = header;
        if (null != header) {
            header.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            if (mAdapter != null)
                mAdapter.addHeaderView(header);
        } else {
            throw new RuntimeException("There is a need to add a view");
        }
    }

    /**
     * 添加尾部视图
     *
     * @param footer
     */
    public void addFooterView(View footer) {
        if (null != footer) {
            footer.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
        } else {
            throw new RuntimeException("There is a need to add a view");
        }
        if (null != mAdapter)
            mAdapter.addFooterView(footer);
        else
            this.footer = footer;
    }

    /**
     * 删除 头部 布局（删除所有头部）
     */

    public void removeHeaderView() {
        mAdapter.removeHeaderView();
    }

    /**
     * 删除 尾部 布局
     */
    public void removeFooterView() {
        mAdapter.removeFooterView();
    }

    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener) {
        mSwipeLayout.setOnRefreshListener(listener);

    }

    public void stopRefresh() {
        mSwipeLayout.setRefreshing(false);
    }

    public void setOnLoadMoreListener(final OnLoadMoreListener listener) {
        final LinearLayoutManager mLayoutManager = (LinearLayoutManager) mRecycleView.getLayoutManager();
        if (!isLoadMore || listener == null) {
            return;
        }
        mRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!isAutoLoadMore && mLayoutManager.findLastVisibleItemPosition() + 1 == mLayoutManager.getItemCount()) {
                        listener.onLoadMore();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isAutoLoadMore && mLayoutManager.findLastVisibleItemPosition() + 1 == mLayoutManager.getItemCount()) {
                    listener.onLoadMore();
                } else if (isAutoLoadMore) {
                    isAutoLoadMore = false;
                }
            }
        });
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}
