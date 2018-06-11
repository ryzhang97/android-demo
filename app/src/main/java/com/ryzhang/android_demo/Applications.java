package com.ryzhang.android_demo;

import android.app.Application;

import com.match.library.db.Error;
import com.match.library.utils.CrashHandler;
import com.ryzhang.android_demo.db.dao.BaseDao;

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
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        initCrash();
    }

    /**
     * 初始化异常
     */
    private void initCrash() {
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        crashHandler.setErrorListenner(error -> {
            BaseDao<Error> errorBaseDao = new BaseDao<>(getApplicationContext(), Error.class);
            errorBaseDao.creat(error);
        });
    }
}
