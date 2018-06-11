package com.ryzhang.android_demo.app;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.match.library.custom.dialog.Loading;
import com.match.library.utils.SharedPreferencesUtils;


/**
 * @author ryzhang
 * @date 2017/12/27
 * @time 14:43
 * Project
 */
public abstract class BaseActivity extends com.match.library.app.BaseActivity {
//    protected HttpRequestUtils httpRequestUtils;
    protected SharedPreferencesUtils preferences;
    protected Loading loading;

    @Override
    protected void initVariable() {
        super.initVariable();
//        httpRequestUtils = HttpRequestUtils.getInstance(context);
        preferences = SharedPreferencesUtils.getInstance(context);
        loading = new Loading(context);
    }

    protected void setTitle(LinearLayout ll, int left, String title, int right) {
        ImageView iv_left = (ImageView) ((LinearLayout) ll.getChildAt(0)).getChildAt(0);
        TextView tv_tiltle = (TextView) ((LinearLayout) ll.getChildAt(1)).getChildAt(0);
        ImageView iv_right = (ImageView) ((LinearLayout) ll.getChildAt(2)).getChildAt(0);
        if (left != 0) {
            iv_left.setImageResource(left);
        }
        tv_tiltle.setText(title);
        if (right != 0) {
            iv_right.setImageResource(right);
        }
    }

    /**
     * 设置标题
     *
     * @param ll
     * @param title
     */
    protected void setTitle(LinearLayout ll, String title) {
        setTitle(ll, 0, title, 0);
    }
}
