package com.ryzhang.android_demo.app.loading;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.match.library.utils.common.ActivityManager;
import com.match.library.utils.dependent.view.annotation.ContentView;
import com.match.library.utils.dependent.x;
import com.ryzhang.android_demo.R;
import com.ryzhang.android_demo.app.main.MainActivity;
import com.ryzhang.android_demo.db.DatabaseHelper;

/**
 * @author ryzhang
 * @date 2017/10/18
 * @time 17:43
 * Project
 */
@ContentView(R.layout.activity_loading)
public class LoadingActivity extends Activity {
    private DatabaseHelper databaseHelper = null;
    protected Context context;
    protected ActivityManager activityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
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
        activityManager.pushActivity(this);
    }

    /**
     * 初始化监听
     */
    protected void initListener() {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        activityManager.destoryActivity((Activity) context);
    }


}
