package com.handy.module.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * @author: handy
 * @date: 2020-06-11
 * @description:
 */
public class LogUtils {

    public static void v(String tag, String content, Object... args) {
        if (args != null && args.length > 0) {
            Log.v(tag, String.format(content, args));
        }
    }

    public static void d(String tag, String message) {
        if (!TextUtils.isEmpty(message)) {
            Log.d(tag, message);
        }
    }

    public static void d(String tag, String message, Object... args) {
        if (args != null && args.length > 0) {
            Log.d(tag, String.format(message, args));
        }
    }

    public static void e(String tag, String message) {
        if (!TextUtils.isEmpty(message)) {
            Log.e(tag, message);
        }
    }

    public static void e(String tag, String message, Object... args) {
        if (args != null && args.length > 0) {
            Log.e(tag, String.format(message, args));
        }
    }

    public static void e(String tag, Throwable throwable) {
        if (throwable != null) {
            e(tag, String.valueOf(throwable));
        }
    }

}
