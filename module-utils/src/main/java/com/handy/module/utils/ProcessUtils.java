package com.handy.module.utils;

import android.content.Context;
import android.os.Build;

import java.lang.reflect.Method;

/**
 * @author: handy
 */
public class ProcessUtils {

    /**
     * 判断进程是否为64为
     * @param context 上下文
     * @return 成功失败
     */
    public static boolean isProcess64(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上6.0以下系统
            return isProcess64Ex(context);
        } else {
            //5.0以下系统不支持64位
            return false;
        }
    }

    /**
     * 5.0-6.0系统判断进程类型
     *
     * @param context
     * @return
     */
    private static boolean isProcess64Ex(Context context) {
        try {
            ClassLoader classLoader = context.getClassLoader();
            Method method = ClassLoader.class.getDeclaredMethod("findLibrary", String.class);
            Object result = method.invoke(classLoader, "art");
            return result != null && String.valueOf(result).contains("lib64");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}