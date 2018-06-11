package com.match.library.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * JSON相关的工具类
 */
public class JsonUtils {
    /**
     * JSON数据转换为集合对象
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> fromJsonList(String json, Class<T> cls) {
        ArrayList<T> mList = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            mList.add(new Gson().fromJson(elem, cls));
        }
        return mList;
    }

    /**
     * JSON数据转换为类对象
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T fromJsonObject(String json, Class<T> cls) {
        return new Gson().fromJson(json, cls);
    }

    /**
     * 类对象转换为JSON数据
     *
     * @param object
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> String toJson(Object object, Class<T> cls) {
        return new Gson().toJson(object, cls);
    }

    /**
     * 类对象转换为JSON数据
     *
     * @param map
     * @return
     */
    public static String toJson(Map<String, Object> map) {
        return new Gson().toJson(map);
    }

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static JSONObject toJSONObject(String json) {
        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            Logcat.e(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
