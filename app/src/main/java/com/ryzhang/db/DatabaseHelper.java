package com.ryzhang.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.ryzhang.utils.common.Logcat;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库帮助类
 *
 * @author ryzhang
 * @date:2017/8/22 17:05
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    /**
     * 数据库名称
     */
    private static final String DB_NAME = "ryzhang.db";

    private static final String TAG = "match";
    /**
     * 数据库版本
     */
    private static final int VERSION = 1;
    /**
     * 存放Dao的map
     */
    private Map<String, Dao> daos = new HashMap<String, Dao>();
    /**
     * 数据库帮助类实例
     */
    private static DatabaseHelper instance;

    private DatabaseHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        Logcat.d(DatabaseHelper.class, "创建数据库表，当前数据版本：" + VERSION);
//        try {
//            TableUtils.createTable(connectionSource, Datadict.class);
//        } catch (SQLException e) {
//            Logcat.e(DatabaseHelper.class,"创建数据库表失败");
//            e.printStackTrace();
//        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
//        Log.d(TAG,"更新数据库表，当前数据版本："+VERSION);
//        try {
//            String sql1 = "alter table tb_datadict add test text";
//            getDao(Datadict.class).executeRawNoArgs(sql1);
////            TableUtils.dropTable(connectionSource, Datadict.class, true);
////            onCreate(sqLiteDatabase, connectionSource);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     */
    public static synchronized DatabaseHelper getHelper(Context context) {
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null) {
                    instance = new DatabaseHelper(context);
                }
            }
        }
        return instance;
    }

    /**
     * 获得Dao
     *
     * @return
     * @throws SQLException
     */
    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();
        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }
        if (dao == null) {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        Log.d(TAG, "关闭数据库");
        super.close();
        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
