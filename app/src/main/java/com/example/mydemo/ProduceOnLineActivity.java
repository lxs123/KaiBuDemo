package com.example.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

/**
 * Created by Administrator on 2018/3/27 0027.
 */

public class ProduceOnLineActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("生产入库");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_produce_online;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onClick1(View view) {
        startActivity(new Intent(ProduceOnLineActivity.this, ReportWorkActivity.class));
    }
}
