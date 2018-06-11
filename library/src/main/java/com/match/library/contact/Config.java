package com.match.library.contact;

/**
 * @author ryzhang
 * @date 2018/1/11
 * @time 11:58
 * Project
 */
public class Config {
    private static final boolean NETWORK = true;
    private static final String HTTP_TEST = "";//测试环境url
    private static final String HTTP_REAL = "";//正式环境url

    public static final String URL;
    public static final String PREFERENCE_FILE_NAME = "ryzhang";

    static {
        if (NETWORK) {//如果是正式环境
            URL = HTTP_REAL;
        } else {
            URL = HTTP_TEST;
        }
    }

}
