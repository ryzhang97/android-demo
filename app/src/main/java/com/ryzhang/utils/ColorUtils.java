package com.ryzhang.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;

/**
 * @author ryzhang
 * @date 2017/9/21
 * @time 10:35
 * 颜色工具类
 */
public class ColorUtils {
    private static ColorUtils instance;
    private Context context;

    private ColorUtils(Context context) {
        this.context = context;
    }

    public static ColorUtils geInstance(Context context) {
        if (instance == null) {
            instance = new ColorUtils(context);
        }
        return instance;
    }

    public int getColor(int colorId) {
        return ContextCompat.getColor(context, colorId);
    }
}
