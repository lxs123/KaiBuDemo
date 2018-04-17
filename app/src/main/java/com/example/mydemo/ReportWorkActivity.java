package com.example.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Administrator on 2018/3/27 0027.
 */

public class ReportWorkActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("报工");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_report_work;
    }

    public void onClick1(View view) {
        startActivity(new Intent(getBaseContext(),MoveActivity.class));
    }
}
