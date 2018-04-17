package com.example.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.mydemo.view.LinearListView;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by Administrator on 2018/3/27 0027.
 */

public class AllocationActivity extends BaseActivity {

    private LinearListView mAllocationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LinearListView mListview = (LinearListView) findViewById(R.id.list_allocation);
//        mListview.setAdapter(new MyAllocationAdapter());
        mAllocationList = (LinearListView) findViewById(R.id.allocation_list);
//        mAllocationList.setLayoutManager(new LinearLayoutManager(this));
        mAllocationList.setAdapter(new MyAdapter());
        setTitle("调拨");
    }

    public void onClick(View view) {
        startActivity(new Intent(AllocationActivity.this, FeedingActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_allocation;
    }

    public class MyAdapter extends BaseAdapter {

        private ViewHolder holder;

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(AllocationActivity.this).inflate(R.layout.listitem_allocation, viewGroup, false);
                holder = new ViewHolder(view);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.mListview.setAdapter(new MyAllocationAdapter());
            return view;
        }

        public class ViewHolder {

            private final LinearListView mListview;

            public ViewHolder(View itemView) {
                AutoUtils.auto(itemView);
                mListview = (LinearListView) itemView.findViewById(R.id.list_allocation);
            }
        }
    }

    public class MyAllocationAdapter extends BaseAdapter {

        private ViewHolder holder;

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(AllocationActivity.this).inflate(R.layout.listitem_allocation_item, viewGroup, false);
                holder = new ViewHolder(view);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            return view;
        }

        public class ViewHolder {
            public ViewHolder(View view) {
                AutoUtils.auto(view);

            }
        }
    }
}
