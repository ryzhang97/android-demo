package com.ryzhang.android_demo.module;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import com.ryzhang.android_demo.db.DatabaseHelper;
import com.ryzhang.utils.common.ActivityManager;
import com.ryzhang.utils.x;

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
    //    private int theme = R.style.AppTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ViewInjectUtil.inject(this);
        x.view().inject(this);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.close();
    }

    /**
     * 改变主题
     */
    protected void changeTheme() {
//        MODE_NIGHT_NO： 使用亮色(light)主题，不使用夜间模式；
//        MODE_NIGHT_YES：使用暗色(dark)主题，使用夜间模式；
//        MODE_NIGHT_AUTO：根据当前时间自动切换 亮色(light)/暗色(dark)主题；
//        MODE_NIGHT_FOLLOW_SYSTEM(默认选项)：设置为跟随系统，通常为 MODE_NIGHT_NO
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        AppCompatDelegate appCompatDelegate = getDelegate();
        int module = currentNightMode == Configuration.UI_MODE_NIGHT_NO ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO;
        appCompatDelegate.setLocalNightMode(module);
        // 需要调用recreate方法使之生效
        recreate();
    }


    /**
     * 初始化
     */
    private void init() {
        loadData();
        initListener();
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
