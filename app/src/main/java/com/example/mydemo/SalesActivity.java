package com.example.mydemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mydemo.BaseActivity;
import com.example.mydemo.R;

/**
 * Created by Administrator on 2018/3/27 0027.
 */

public class SalesActivity extends BaseActivity {

    private RecyclerView mPickList;
    private MyPickAdapter adapter;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("销货");
        mPickList = (RecyclerView) findViewById(R.id.picking_list);
        mPickList.setLayoutManager(new LinearLayoutManager(this));
        mPickList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new MyPickAdapter();
        mPickList.setAdapter(adapter);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_picking;
    }
    public void onClick3(View view) {
        adapter.setClearSel();
    }

    public void onClick1(View view) {
        startActivity(new Intent(SalesActivity.this, AllocationActivity.class));
    }


    public class MyPickAdapter extends RecyclerView.Adapter<MyPickAdapter.ViewHolder> {
        private Boolean[] isSel = new Boolean[]{false, false, false, false, false, false, false, false, false};

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_sales, null, false);
            return new ViewHolder(view);
        }

        public void setClearSel() {
            for (int i = 0; i < isSel.length; i++) {
                isSel[i] = false;
            }
            notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            if (isSel[position]) {
                holder.imgvSel.setImageResource(R.drawable.ic_sel_click);
            } else {
                holder.imgvSel.setImageResource(R.drawable.ic_default);
            }
            holder.linlayContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isSel[position]) {
                        holder.imgvSel.setImageResource(R.drawable.ic_sel_click);
                    } else {
                        holder.imgvSel.setImageResource(R.drawable.ic_default);
                    }
                    isSel[position] = !isSel[position];

                    // 强制隐藏软键盘
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            });
            if (position == 8) {
                holder.linlayMore.setVisibility(View.VISIBLE);
            } else {
                holder.linlayMore.setVisibility(View.GONE);
            }
            holder.tvMoreClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final ProgressDialog progressDialog = ProgressDialog.show(SalesActivity.this, "", "正在加载更多……", false, true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                        }
                    }, 2000);
                }
            });

        }

        @Override
        public int getItemCount() {
            return 9;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private final LinearLayout linlayContent;
            private final ImageView imgvSel;
            private final LinearLayout linlayMore;
            private final TextView tvMoreClick;

            public ViewHolder(View itemView) {
                super(itemView);
                linlayContent = (LinearLayout) itemView.findViewById(R.id.list_picking_content);
                imgvSel = (ImageView) itemView.findViewById(R.id.list_sel_img);
                linlayMore = (LinearLayout) itemView.findViewById(R.id.list_more_content);
                tvMoreClick = (TextView) itemView.findViewById(R.id.list_more_click);
            }
        }
    }
}
