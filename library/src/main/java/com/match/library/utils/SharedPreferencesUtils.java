package com.match.library.utils;

import android.content.Context;
import android.content.SharedPreferences;


import com.match.library.contact.Config;

import java.util.Iterator;
import java.util.Map;

/**
 * SharedPreferences帮助工具类
 */
public class SharedPreferencesUtils {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor shareEditor;
    private static SharedPreferencesUtils sharedPreferencesUtils = null;

    private SharedPreferencesUtils(Context context) {
        sharedPreferences = context.getSharedPreferences(Config.PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        shareEditor = sharedPreferences.edit();
    }

    /**
     * 获取SharedPreferences帮助工具类实例
     *
     * @param context
     * @return
     */
    public static SharedPreferencesUtils getInstance(Context context) {
        if (sharedPreferencesUtils == null) {
            synchronized (SharedPreferencesUtils.class) {
                if (sharedPreferencesUtils == null) {
                    sharedPreferencesUtils = new SharedPreferencesUtils(context);
                }
            }
        }
        return sharedPreferencesUtils;
    }

    /**
     * 根据key获取字符串参数，省缺值设置为空
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        return getString(key, "");
    }

    /**
     * 根据key获取字符串参数
     *
     * @param key
     * @param defaultString 省缺值
     * @return
     */
    public String getString(String key, String defaultString) {
        return sharedPreferences.getString(key, defaultString);
    }

    public void putString(Map<String, String> map) {
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            shareEditor.putString(entry.getKey(), entry.getValue());
        }
        shareEditor.commit();
    }

    /**
     * 保存数据
     *
     * @param key
     * @param value
     */
    public void putString(String key, String value) {
        shareEditor.putString(key, value).commit();
    }

    /**
     * 保存空数据
     *
     * @param key
     */
    public void putString(String key) {
        putString(key, "");
    }

    /**
     * 获取int   数据
     *
     * @param key
     * @param var
     * @return
     */
    public int getInt(String key, int var) {
        return sharedPreferences.getInt(key, var);
    }

    /**
     * put  int 数据
     *
     * @param key
     * @param var
     */
    public void putInt(String key, int var) {
        shareEditor.putInt(key, var);
    }

    /**
     * 移除数据
     *
     * @param var
     */
    public void remove(String var) {
        shareEditor.remove(var);
    }

    public void commit() {
        shareEditor.commit();
    }

    public boolean commitReturn() {
        return shareEditor.commit();
    }

    public boolean getAppCode(String appCode) {
        return getString("AppCode").contains(appCode);
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void putBoolean(String key, boolean value) {
        shareEditor.putBoolean(key, value);
        shareEditor.commit();
    }

    public void clear() {
        shareEditor.clear();
        shareEditor.commit();
    }
}
