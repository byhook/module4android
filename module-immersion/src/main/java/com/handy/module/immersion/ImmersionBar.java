package com.handy.module.immersion;

import android.app.Activity;
import android.os.Build;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * @author: handy
 * @date: 2020-06-25
 * @description:
 */
public class ImmersionBar {


    public static StatusBar with(Activity activity){
        return new StatusBar(activity);
    }

}
