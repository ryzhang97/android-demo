package com.ryzhang.android_demo.app.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.match.library.utils.dependent.x;
import com.match.library.utils.statusbar.StatusBar;


/**
 * @author ryzhang
 * @date 2017/11/6
 * @time 16:12
 * Project
 */
public abstract class BaseFragment extends Fragment {

    public BaseFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        onCreateView();
        onListener();
        return view;
    }

    /**
     * 填充状态栏的textview,颜色
     *
     * @param status_bar
     * @param color
     */
    protected void initStatusBar(TextView status_bar, int color) {
        int getStatusBarHeight = StatusBar.getStatusBarHeight(getContext());
        status_bar.setHeight(getStatusBarHeight);
        status_bar.setBackgroundResource(color);
    }

    protected abstract void onCreateView();
    protected abstract void onListener();
}