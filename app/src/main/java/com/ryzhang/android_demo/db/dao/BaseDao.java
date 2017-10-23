package com.ryzhang.android_demo.db.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.ryzhang.android_demo.db.DatabaseHelper;

import java.sql.SQLException;

/**
 * @author ryzhang
 * @date 2017/10/23
 * @time 11:53
 * Project
 */
public class BaseDao {
    protected Context context;
    protected Class claz;
    protected Dao<Object, Integer> daoOpe;
    protected DatabaseHelper helper;

    public BaseDao(Context context, Class claz) {
        this.context = context;
        this.claz = claz;
        helper = DatabaseHelper.getHelper(context);
        try {
            daoOpe = helper.getDao(claz);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
