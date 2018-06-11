package com.match.library.custom.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;

/**
 * @author ryzhang
 * @date 2017/12/29
 * @time 18:16
 * Project 对话框
 */
public class Dialog extends AlertDialog {
    private int resource;
    private int gravity = Gravity.CENTER;

    protected Dialog(Context context) {
        super(context);
    }

    public Dialog(Context context, int resource) {
        this(context);
        this.resource = resource;
    }

    public Dialog(Context context, int resource, int gravity) {
        this(context, resource);
        this.gravity = gravity;
    }

    protected Dialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(resource);
        if (gravity == Gravity.BOTTOM) {
            setWindow(Gravity.BOTTOM);
        }
    }

    protected void setWindow(int gravity) {
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = gravity;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //就是这个属性导致不能获取焦点,默认的是FLAG_NOT_FOCUSABLE,故名思义不能获取输入焦点,
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);
        //设置对话框全屏
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }
}
