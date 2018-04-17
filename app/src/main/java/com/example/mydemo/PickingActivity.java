package com.example.mydemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class PickingActivity extends BaseActivity {

    private RecyclerView mPickList;
    private MyPickAdapter adapter;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("领料");
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
        startActivity(new Intent(PickingActivity.this, AllocationActivity.class));
    }

    public class MyPickAdapter extends RecyclerView.Adapter<MyPickAdapter.ViewHolder> {
        private Boolean[] isSel = new Boolean[]{false, false, false, false, false, false, false, false, false};

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_picking, null, false);
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

        }

        @Override
        public int getItemCount() {
            return 9;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private final LinearLayout linlayContent;
            private final ImageView imgvSel;

            public ViewHolder(View itemView) {
                super(itemView);
                linlayContent = (LinearLayout) itemView.findViewById(R.id.list_picking_content);
                imgvSel = (ImageView) itemView.findViewById(R.id.list_sel_img);
            }
        }
    }
}
