package com.ryzhang.android_demo.module;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ryzhang.android_demo.db.DatabaseHelper;
import com.ryzhang.utils.annotation.ViewInjectUtil;
import com.ryzhang.utils.common.ActivityManager;

/**
 * @author ryzhang
 * @date 2017/10/18
 * @time 17:43
 * Project
 */
public abstract class BaseActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper = null;
    protected Context context;
    protected ActivityManager activityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtil.inject(this);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        loadData();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.close();
    }

    /**
     * 加载数据
     */
    protected void loadData() {
        context = this;
        databaseHelper = DatabaseHelper.getHelper(context);
        activityManager = ActivityManager.getActivityManager();
        activityManager.pushActivity((Activity) context);
    }

    /**
     * 初始化监听
     */
    protected abstract void initListener();


}
