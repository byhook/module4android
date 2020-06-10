package com.handy.module.utils;

import android.content.Context;
import android.os.Build;
import android.os.Process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import dalvik.system.PathClassLoader;

/**
 * @author: handy
 * @date: 2019-06-21
 * @description:
 */
public class ProcessUtils {

    /**
     * 判断进程是否为64为
     *
     * @param context
     * @return
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