package com.ryzhang.utils.common;

import android.content.Context;
import android.support.v4.content.ContextCompat;

/**
 * @author ryzhang
 * @date 2017/9/21
 * @time 10:35
 * 颜色工具类
 */
public class ColorConvert {
    private static ColorConvert instance;
    private Context context;

    private ColorConvert(Context context) {
        this.context = context;
    }

    public static ColorConvert geInstance(Context context) {
        if (instance == null) {
            instance = new ColorConvert(context);
        }
        return instance;
    }

    public int getColor(int colorId) {
        return ContextCompat.getColor(context, colorId);
    }

//    public Color getColor(String colorString){
//        return ContextCompat.getColor(context,)
//    }
}
