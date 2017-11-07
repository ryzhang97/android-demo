package com.ryzhang.utils.common;

import android.util.Log;

import com.ryzhang.android_demo.BuildConfig;

/**
 * @author ryzhang
 * @date 2017/9/27
 * @time 14:35
 * Project
 */
public class Logcat {
    public static final String TAG = "ryzhang";
    private static boolean isOpen = BuildConfig.LOG;//是否打开log
    private static boolean isWhole = true;//是否全部显示log
    private static Class isClass = Logcat.class;//是否全部显示log

    /**
     * 打印默认tag log
     *
     * @param content
     */
    public static void d(String content) {
        d(isClass, content);
    }

    /**
     * 打印tag为该类的Log
     *
     * @param claz
     * @param content
     */
    public static void d(Class claz, String content) {
        if (isOpen) {//打开log
            if (isWhole) {//显示全部log
                d(TAG, content);
            } else {
                if (claz.equals(isClass)) {
                    d(TAG, content);
                } else {
                    d(claz.getSimpleName(), content);
                }
            }
        }
    }

    /**
     * 打印指定tag log
     *
     * @param content
     */
    public static void d(String tag, String content) {
        Log.d(tag, content);
    }

    public static void e(String content) {
        e(isClass, content);
    }

    public static void e(Class claz, String content) {
        if (isOpen) {//打开log
            if (isWhole) {//显示全部log
                e(TAG, content);
            } else {
                if (claz.equals(isClass)) {
                    e(TAG, content);
                } else {
                    e(claz.getSimpleName(), content);
                }
            }
        }
    }

    public static void e(String tag, String content) {
        Log.e(tag, content);
    }

    public static void e(String tag, String content, Throwable tr) {
        Log.e(tag, content, tr);
    }
}
