package com.example.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class PurchaseAty extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("采购入库");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_purchase;
    }

    public void onClick2(View view) {
        startActivity(new Intent(PurchaseAty.this, PickingActivity.class));
    }
}
