package com.ryzhang.android_demo.db.dao;

import android.content.Context;

import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.match.library.utils.Logcat;
import com.ryzhang.android_demo.db.DatabaseHelper;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ryzhang
 * @date 2017/10/23
 * @time 11:53
 * Project
 */
public class BaseDao<T> {
    protected Dao<T, ?> daoOpe;
    protected DatabaseHelper helper;
    protected Class<T> clazz;

    public BaseDao(Context context, Class<T> claz) {
        this.clazz = claz;
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
        RuntimeExceptionDao<T, String> dao = helper.getRuntimeExceptionDao(clazz);
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
        DeleteBuilder<T, ?> deleteBuilder = daoOpe.deleteBuilder();
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

//    public int deleteById(int id) {
//        int result = 0;
//        try {
//            result = daoOpe.deleteById(id);
//        } catch (SQLException e) {
//            Logcat.e(getClass(), "删除失败");
//            e.printStackTrace();
//        }
//        return result;
//    }

    /**
     * 删除所有表格数据
     *
     * @return
     */
    public int deleteAll() {
        int result = 0;
        DeleteBuilder<T, ?> deleteBuilder = daoOpe.deleteBuilder();
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

//    /**
//     * 更新id
//     *
//     * @param t
//     * @param id
//     * @return
//     */
//    public int update(T t, int id) {
//        int result = 0;
//        try {
//            result = daoOpe.updateId(t, id);
//        } catch (SQLException e) {
//            Logcat.e(getClass(), "更新数据失败");
//            e.printStackTrace();
//        }
//        return result;
//    }

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

    public List<T> query(HashMap<String, String> hashMap, String tableName) {
//        StringBuffer stringBuffer = new StringBuffer("select * from " + tableName + " where");
//        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
//            stringBuffer.append(" and ");
//            stringBuffer.append(entry.getKey());
//            String[] arrys = entry.getValue().split("，|,");
//            stringBuffer.append(" " + arrys[0]);
//            if (arrys.length > 1) {
//                for (int i = 1; i < arrys.length; i++) {
//                    stringBuffer.append(" or ");
//                    stringBuffer.append(entry.getKey());
//                    stringBuffer.append(" " + arrys[i]);
//                }
//            }
//        }

        QueryBuilder queryBuilder = daoOpe.queryBuilder();
        Where where = queryBuilder.where();
        try {
            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                String[] arrys = entry.getValue().split("，|,");
                if (arrys.length > 1) {
                    for (int i = 0; i < arrys.length; i++) {
                        where = where.or().eq(entry.getKey(), arrys[i]);
                    }
                } else {
                    where = where.and().eq(entry.getKey(), entry.getValue());
                }
                return where.query();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<T> query(HashMap<String, String> hashMap) {

        QueryBuilder queryBuilder = daoOpe.queryBuilder();
        Where where = queryBuilder.where();
        try {
            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                String[] arrys = entry.getValue().split("，|,");
                if (arrys.length > 1) {
                    if (!where.toString().equals("empty where clause")) {
                        where = where.and();
                    }
                    where = where.eq(entry.getKey(), arrys[0]);
                    for (int i = 1; i < arrys.length; i++) {
                        where = where.or().eq(entry.getKey(), arrys[i]);
                    }
                } else {
                    if (!where.toString().equals("empty where clause")) {
                        where = where.and();
                    }
                    where = where.eq(entry.getKey(), entry.getValue());
                }
            }
            return where.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
