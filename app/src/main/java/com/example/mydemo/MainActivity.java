package com.example.mydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zhy.autolayout.AutoLayoutActivity;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("到货验收");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    public void onClick(View v) {
        startActivity(new Intent(MainActivity.this,OnLineAty.class));
    }
}
