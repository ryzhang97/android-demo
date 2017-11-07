package com.ryzhang.android_demo;

import android.app.Application;

import com.ryzhang.utils.x;

/**
 * @author ryzhang
 * @date 2017/10/18
 * @time 11:37
 * Project
 */
public class Applications extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
//        SqlScoutServer.create(this, getPackageName());
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
