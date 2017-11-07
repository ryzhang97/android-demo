package com.ryzhang.android_demo.module.fragment;

import android.support.constraint.ConstraintLayout;
import android.widget.TextView;

import com.ryzhang.android_demo.R;
import com.ryzhang.utils.view.annotation.ContentView;
import com.ryzhang.utils.view.annotation.ViewInject;
@ContentView(R.layout.fragment_draw3)
public class Draw3Fragment extends BaseFragment {
    @ViewInject(R.id.status_bar)
    private TextView status_bar;//状态栏
    @ViewInject(R.id.app_draw_title)
    private ConstraintLayout app_draw_title;//标题栏

    public Draw3Fragment() {
    }

    @Override
    protected void onCreateView() {
        initStatusBar(status_bar, R.color.colorAccent);
        app_draw_title.setBackgroundResource(R.color.colorAccent);
    }

    @Override
    protected void onListener() {

    }

}
