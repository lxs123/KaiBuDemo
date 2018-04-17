package com.example.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Administrator on 2018/3/27 0027.
 */

public class BinningActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle("装箱");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_binning;
    }

    public void onClick(View view) {
        startActivity(new Intent(BinningActivity.this,ProduceOnLineActivity.class));
    }
}
