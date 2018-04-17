package com.example.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Administrator on 2018/3/27 0027.
 */

public class MoveActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("转移");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_move;
    }

    public void onClick2(View view) {
        startActivity(new Intent(MoveActivity.this, SalesActivity.class));
    }
}
