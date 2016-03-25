package com.ddanwang.tourism.util;

import android.content.Context;

import com.ddanwang.tourism.global.MyApp;

import io.realm.BuildConfig;


/**
 * 日志工具类
 *
 * @author dong 2014-11-18
 */
public class L {
    private static final String TAG = "log";
    private static Context sContext = MyApp.getInstance();

    public static String getCurrentMethodName() {
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }

    public static void v(String tag, int id) {
        log(android.util.Log.VERBOSE, tag, sContext.getResources().getString(id));
    }

    public static void v(String tag, String msg) {
        log(android.util.Log.VERBOSE, tag, msg);
    }

    public static void v(String tag, String format, Object... args) {
        log(android.util.Log.VERBOSE, tag, format, args);
    }

    public static void d(String tag, int id) {
        log(android.util.Log.DEBUG, tag, sContext.getResources().getString(id));
    }

    public static void d(String tag, String msg) {
        log(android.util.Log.DEBUG, tag, msg);
    }

    public static void d(String tag, String format, Object... args) {
        log(android.util.Log.DEBUG, tag, format, args);
    }

    public static void i(String tag, int id) {
        log(android.util.Log.INFO, tag, sContext.getResources().getString(id));
    }

    public static void i(String tag, String msg) {
        log(android.util.Log.INFO, tag, msg);
    }

    public static void i(String tag, String format, Object... args) {
        log(android.util.Log.INFO, tag, format, args);
    }

    public static void w(String tag, int id) {
        log(android.util.Log.WARN, tag, sContext.getResources().getString(id));
    }

    public static void w(String tag, String msg) {
        log(android.util.Log.WARN, tag, msg);
    }

    public static void e(String tag, int id) {
        log(android.util.Log.ERROR, tag, sContext.getResources().getString(id));
    }

    public static void e(String tag, String msg) {
        log(android.util.Log.ERROR, tag, msg);
    }

    private static void log(int priority, String tag, String format, Object... args) {
        log(priority, tag, String.format(format, args));
    }

    private static void log(int priority, String tag, String msg) {
        if (BuildConfig.DEBUG) {
            // debug模式默认打印log，release包不打印log
            android.util.Log.println(priority, tag, msg);
        }
    }
}
