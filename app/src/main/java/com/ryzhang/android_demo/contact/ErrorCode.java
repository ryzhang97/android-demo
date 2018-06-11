package com.ryzhang.android_demo.contact;

/**
 * @author ryzhang
 * @date 2018/1/11
 * @time 9:54
 * Project
 */
public class ErrorCode {
    public static final int ERROR_UNKNOW = -1;
    public static final int NETWORK_IO_EXCEPTION = 0;
    public static final int NETWORK_RESOURCE_NOT_FIND = 1;
    public static final int NETWORK_READ_TIME_OUT = 2;
    public static final int NETWORK_INVALID_URL = 3;
    public static final int FULL_STORAGE = 4;
    public static final int STORAGE_INVALID = 5;
    public static final int STORAGE_SHARED = 13;
    public static final int DOWNLOAD_TEMPLE_FILE_DELETE = 6;
    public static final int DOWNLOAD_WRITE_FAILED = 7;
    public static final int PLAY_NO_FILE = 8;
    public static final int PLAY_MEDIA_ERROR_SERVER_DIED = 9;
    public static final int PLAY_FORMAT_ERROR = 10;
    public static final int PLAY_STATE_ERROR = 11;
    public static final int PLAY_HTTP_ERROR = 14;
    public static final int PLAY_RESOURCE_ERROR = 15;
    public static final int RECORD_EXCEPTION = 12;

    public static String getErrorString(int errorCode)
    {
        switch (errorCode) {
            case 0:
                return "网络很不给力哦，再试一次吧";
            case 1:
                return "服务器无资源";
            case 2:
                return "网络超时";
            case 3:
                return "无效URL";
            case 4:
                return "SD卡剩余空间不足";
            case 5:
                return "未发现SD卡，无法下载歌曲资源！";
        }

        return null;
    }
}
