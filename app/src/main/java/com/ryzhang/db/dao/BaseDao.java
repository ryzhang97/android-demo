package com.ryzhang.db.dao;

import android.content.Context;

import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.ryzhang.android_demo.db.DatabaseHelper;
import com.ryzhang.utils.common.Logcat;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

/**
 * @author ryzhang
 * @date 2017/10/23
 * @time 11:53
 * Project
 */
public class BaseDao<T> {
    protected Dao<T, Integer> daoOpe;
    protected DatabaseHelper helper;
    protected Class claz;

    public BaseDao(Context context, Class claz) {
        this.claz = claz;
        helper = DatabaseHelper.getHelper(context);
        try {
            daoOpe = helper.getDao(claz);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建一条数据
     *
     * @param t
     * @return 插入成功条数
     */
    public int creat(T t) {
        int result = 0;
        try {
//            在表中添加一条记录
            result = daoOpe.create(t);
//            在表中添加一条记录，如果表不存在这条数据,根据设置的主键来判断是否存在
//            daoOpe.createIfNotExists(t);
//            在表中添加一条记录，如果存在则更新主键对应的一条记录，
//            daoOpe.createOrUpdate(t);
        } catch (SQLException e) {
            Logcat.e(getClass(), "创建失败");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 创建多条数据
     *
     * @param list
     * @return 插入成功条数
     */
    public int creat(List<T> list) {
        int result = 0;
        // ORMLite的数据连接封装类
        AndroidDatabaseConnection adc = new AndroidDatabaseConnection(helper.getWritableDatabase(), true);
        // 设置要开启事务的DAO不自动提交代码
        RuntimeExceptionDao<T, String> dao = helper.getRuntimeExceptionDao(claz);
        // 存储点名称为create_claxx
        Savepoint sp = null;
        try {
            adc.setSavePoint("create_claxx");
            for (T t : list) {
                daoOpe.create(t);
            }
            adc.commit(sp);
            result = list.size();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                // 发生异常时进行回滚
                adc.rollback(sp);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 删除 列名=columnName，值=value的数据
     *
     * @param columnName
     * @param value
     * @return
     */
    public int delete(String columnName, String value) {
        int result = 0;
        DeleteBuilder<T, Integer> deleteBuilder = daoOpe.deleteBuilder();
        try {
            deleteBuilder.where().eq(columnName, value);
            result = deleteBuilder.delete();
        } catch (java.sql.SQLException e) {
            Logcat.e(getClass(), "删除失败");
            e.printStackTrace();
        }
        return result;
    }

    public int delete(T t) {
        int result = 0;
        try {
            result = daoOpe.delete(t);
        } catch (SQLException e) {
            Logcat.e(getClass(), "删除失败");
            e.printStackTrace();
        }
        return result;
    }

    public int deleteById(int id) {
        int result = 0;
        try {
            result = daoOpe.deleteById(id);
        } catch (SQLException e) {
            Logcat.e(getClass(), "删除失败");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除所有表格数据
     *
     * @return
     */
    public int deleteAll() {
        int result = 0;
        DeleteBuilder<T, Integer> deleteBuilder = daoOpe.deleteBuilder();
        try {
            result = deleteBuilder.delete();
        } catch (SQLException e) {
            Logcat.e(getClass(), "全部删除失败");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新数据 id为唯一标志
     *
     * @param t
     * @return
     */
    public int update(T t) {
        int result = 0;
        try {
            result = daoOpe.update(t);
        } catch (SQLException e) {
            Logcat.e(getClass(), "更新数据失败");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新id
     *
     * @param t
     * @param id
     * @return
     */
    public int update(T t, int id) {
        int result = 0;
        try {
            result = daoOpe.updateId(t, id);
        } catch (SQLException e) {
            Logcat.e(getClass(), "更新数据失败");
            e.printStackTrace();
        }
        return result;
    }
//
//    public long select() {
//        QueryBuilder queryBuilder = daoOpe.queryBuilder();
//        try {
//            return queryBuilder.countOf();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }

    public T queryForFirst() {
        QueryBuilder queryBuilder = daoOpe.queryBuilder();
        T t = null;
        try {
            t = (T) queryBuilder.queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

}
