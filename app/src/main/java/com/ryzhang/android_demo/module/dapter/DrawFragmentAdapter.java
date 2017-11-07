package com.ryzhang.android_demo.module.dapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryzhang
 * @date 2017/11/6
 * @time 14:39
 * Project
 */
public class DrawFragmentAdapter extends FragmentPagerAdapter {
    //存储所有的fragment
    private List<Fragment> fragmentList;

    public DrawFragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
