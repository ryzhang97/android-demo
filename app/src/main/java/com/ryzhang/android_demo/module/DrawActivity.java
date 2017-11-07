package com.ryzhang.android_demo.module;

import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.RadioButton;

import com.ryzhang.android_demo.R;
import com.ryzhang.android_demo.module.dapter.DrawFragmentAdapter;
import com.ryzhang.android_demo.module.fragment.Draw1Fragment;
import com.ryzhang.android_demo.module.fragment.Draw2Fragment;
import com.ryzhang.android_demo.module.fragment.Draw3Fragment;
import com.ryzhang.utils.statusbar.StatusBar;
import com.ryzhang.utils.view.annotation.ContentView;
import com.ryzhang.utils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.activity_draw)
public class DrawActivity extends BaseActivity {
    @ViewInject(R.id.drawer_layout)
    DrawerLayout drawer;
    //    @ViewInject(R.id.status_bar)
//    TextView status_bar;
    //    @ViewInject(R.id.app_draw_title)
//    ConstraintLayout app_draw_title;//标题栏
    @ViewInject(R.id.rb_selector_app_title_bottom1)
    RadioButton rb_1;
    @ViewInject(R.id.rb_selector_app_title_bottom2)
    RadioButton rb_2;
    @ViewInject(R.id.rb_selector_app_title_bottom3)
    RadioButton rb_3;
    @ViewInject(R.id.vp_app_draw_content)
    ViewPager vp_content;

    //fragment列表
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    //底部title点击事件
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.rb_selector_app_title_bottom1) {
                vp_content.setCurrentItem(0);
            } else if (view.getId() == R.id.rb_selector_app_title_bottom2) {
                vp_content.setCurrentItem(1);
            } else if (view.getId() == R.id.rb_selector_app_title_bottom3) {
                vp_content.setCurrentItem(2);
            }
        }
    };

    @Override
    protected void initListener() {
        rb_1.setOnClickListener(onClickListener);
        rb_2.setOnClickListener(onClickListener);
        rb_3.setOnClickListener(onClickListener);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void loadData() {
        super.loadData();
        StatusBar.translucentStatusBar(this);
        initViewPage();
    }

    /**
     * 初始化viewpage
     */
    void initViewPage() {
        fragmentList.add(new Draw1Fragment());
        fragmentList.add(new Draw2Fragment());
        fragmentList.add(new Draw3Fragment());
        DrawFragmentAdapter drawFragmentAdapter = new DrawFragmentAdapter(getSupportFragmentManager(), fragmentList);
        vp_content.setAdapter(drawFragmentAdapter);
        vp_content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    rb_1.setChecked(true);
                } else if (position == 1) {
                    rb_2.setChecked(true);
                } else if (position == 2) {
                    rb_3.setChecked(true);
                }
            }

            /**
             * 这个方法在手指操作屏幕的时候发生变化。
             * @param state 0（END：结束）,1(PRESS：按下) , 2(UP：滑动) 。
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
