package com.handy.module.core;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

/**
 * @author: handy
 * @date: 2020-06-09
 * @description:
 */
public class ModuleCore {

    private static volatile ModuleCore sInstance;

    private volatile static Application sApplication;

    private volatile static boolean sEnableLog;

    public static void init(Application application) {
        sApplication = application;
    }

    public static void enableLog(boolean enable) {
        sEnableLog = enable;
    }

    public static boolean isLogEnabled() {
        return sEnableLog;
    }

    public static Application getApplication() {
        if (sApplication == null) {
            throw new IllegalStateException("you must init first");
        }
        return sApplication;
    }

    public static Context getAppContext() {
        return getApplication().getApplicationContext();
    }

    public static Resources getResources() {
        return getAppContext().getResources();
    }

}
