package com.example.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/28 0028.
 */

public class MyTextAty extends BaseActivity {

    private SwipeRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("Name", "www.tjcubertech.com");
        dataMap.put("Password", "tjcubertechWLG");
        dataMap.put("Version", "1.0");
        dataMap.put("Times", new SimpleDateFormat("yyyyMMddhhmmss")
                .format(new Date()));
        dataMap.put("ActionType", "1001");
        Gson gson = new Gson();
        Type listType = new TypeToken<Map<String, String>>() {
        }.getType();
        String dataJsonString = gson.toJson(dataMap, listType);
        Log.d("12233",dataJsonString);
        recyclerView = (SwipeRecyclerView) findViewById(R.id.swipe_list);
        recyclerView.getLayoutManager().setLayoutManager(new LinearLayoutManager(this));
        View header = LayoutInflater.from(this).inflate(R.layout.include_toolbar, null);
        recyclerView.addHeaderView(header);
        recyclerView.setAdapter(new MyPickAdapter());
        View header1 = LayoutInflater.from(this).inflate(R.layout.include_toolbar, null);
        recyclerView.addFooterView(header1);
        recyclerView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.stopRefresh();
                    }
                }, 1500);

            }
        });
        recyclerView.setOnLoadMoreListener(new SwipeRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                final Toast toast = Toast.makeText(getBaseContext(), "加载……", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 1000);
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.aty_text;
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
//            if (isSel[position]) {
//                holder.imgvSel.setImageResource(R.drawable.ic_sel_click);
//            } else {
//                holder.imgvSel.setImageResource(R.drawable.ic_default);
//            }
            holder.linlayContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position < 9) {
                        if (!isSel[position]) {
                            holder.imgvSel.setImageResource(R.drawable.ic_sel_click);
                        } else {
                            holder.imgvSel.setImageResource(R.drawable.ic_default);
                        }
                        isSel[position] = !isSel[position];
                    }
                    startActivity(new Intent(getApplicationContext(), RXJavaTestAty.class));
                    recyclerView.removeHeaderView();
                }
            });

        }

        @Override
        public int getItemCount() {
            return 19;
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
