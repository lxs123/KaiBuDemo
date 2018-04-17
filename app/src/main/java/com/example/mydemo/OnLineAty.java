package com.example.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class OnLineAty extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("上架");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_online;
    }
    public void onClick1(View view){
        startActivity(new Intent(OnLineAty.this,PurchaseAty.class));
    }
}
