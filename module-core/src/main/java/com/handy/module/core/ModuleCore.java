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

    private static Application sApplication;

    public static void init(Application application){
        sApplication = application;
    }

    public static Application getApplication(){
        if(sApplication == null){
            throw new IllegalStateException("you must init first");
        }
        return sApplication;
    }

    public static Context getAppContext(){
        return getApplication().getApplicationContext();
    }

    public static Resources getResources(){
        return getAppContext().getResources();
    }

}
