package com.match.library.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.match.library.db.Error;
import com.match.library.utils.common.CalendarUtil;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ryzhang
 * @date 2018/1/11
 * @time 18:00
 * Project
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

//    public static final String TAG = "CrashHandler";

    //系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    //CrashHandler实例
    private static CrashHandler INSTANCE = new CrashHandler();
    //程序的Context对象
    private Context mContext;
    //用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<>();

    private IError iError;

    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashHandler() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    public void setErrorListenner(IError iError) {
        this.iError = iError;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        //收集设备参数信息
        collectDeviceInfo(mContext);
        saveCrashInfo2DB(ex);
        return true;
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Logcat.e("an error occured when collect package info");
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Logcat.d(field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Logcat.e("an error occured when collect crash info");
            }
        }
    }

    /**
     * 保存错误信息到数据库中
     *
     * @param ex
     */
    private void saveCrashInfo2DB(Throwable ex) {
        Error error = new Error();
        error.setDateTime(CalendarUtil.YEAR_TO_SECOND);
        if (infos.containsKey("SERIAL")) {
            error.setSerial(infos.get("SERIAL"));
        }
        if (infos.containsKey("MODEL")) {
            error.setModle(infos.get("MODEL"));
        }
        if (infos.containsKey("versionName")) {
            error.setVersion(infos.get("versionName"));
        }
        error.setCause(readCause(ex));
        if (iError != null) {
            iError.onErrorListener(error);
        }
    }

    /**
     * 读取错误原因
     *
     * @param ex
     * @return
     */
    private String readCause(Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        return writer.toString();
    }

    public interface IError {
        void onErrorListener(Error error);
    }
}
