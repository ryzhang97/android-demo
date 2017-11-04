package com.ryzhang.android_demo.module;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.widget.TextView;

import com.ryzhang.android_demo.R;
import com.ryzhang.utils.annotation.ContentView;
import com.ryzhang.utils.annotation.ViewInject;
import com.ryzhang.utils.statusbar.StatusBar;

import java.lang.reflect.Field;

@ContentView(R.layout.activity_draw)
public class DrawActivity extends BaseActivity {
    @ViewInject(R.id.drawer_layout)
    DrawerLayout drawer;

    @Override
    protected void initListener() {
        StatusBar.translucentStatusBar(this);
        int getStatusBarHeight = getStatusBarHeight();
        TextView statu_bar = findViewById(R.id.status_bar);
        statu_bar.setHeight(getStatusBarHeight);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 通过反射的方式获取状态栏高度
     *
     * @return
     */
    private int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
