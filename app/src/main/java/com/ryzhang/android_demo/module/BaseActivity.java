package com.ryzhang.android_demo.module;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ryzhang.android_demo.db.DatabaseHelper;

/**
 * @author ryzhang
 * @date 2017/10/18
 * @time 17:43
 * Project
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Context context;
    //    protected DatabaseManager databaseManager = null;
    protected DatabaseHelper databaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        initView();
        loadData();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.close();
    }

    /**
     * 初始化布局
     */
    protected abstract void initView();

    /**
     * 加载数据
     */
    protected void loadData() {
        context = this;
        databaseHelper = DatabaseHelper.getHelper(context);
    }

    /**
     * 初始化监听
     */
    protected abstract void initListener();


}
