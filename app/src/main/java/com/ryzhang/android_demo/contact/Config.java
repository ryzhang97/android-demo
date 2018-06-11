package com.ryzhang.android_demo.contact;

import com.ryzhang.android_demo.BuildConfig;

/**
 * @author ryzhang
 * @date 2018/1/11
 * @time 11:58
 * Project
 */
public class Config {
    private static final boolean NETWORK = BuildConfig.NETWORK;
    private static final String HTTP_TEST = "";//测试环境url
    private static final String HTTP_REAL = "";//正式环境url

    public static final String URL;

    static {
        if (NETWORK) {//如果是正式环境
            URL = HTTP_REAL;
        } else {
            URL = HTTP_TEST;
        }
    }

}
