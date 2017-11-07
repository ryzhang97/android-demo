package com.ryzhang.android_demo.module.app;

import android.app.Activity;
import android.content.Intent;

import com.ryzhang.android_demo.R;
import com.ryzhang.android_demo.module.DrawActivity;
import com.ryzhang.utils.view.annotation.ContentView;

@ContentView(R.layout.activity_app_loading)
public class LoadingActivity extends BaseActivity {
    @Override
    protected void initListener() {
        Intent intent = new Intent(context, DrawActivity.class);
        startActivity(intent);
        activityManager.destoryActivity((Activity) context);
    }
}
