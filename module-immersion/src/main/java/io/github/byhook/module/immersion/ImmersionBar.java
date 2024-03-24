package io.github.byhook.module.immersion;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;

/**
 * @author: handy
 * @date: 2020-06-25
 * @description:
 */
public class ImmersionBar {

    private Window mWindow;

    private ViewGroup mDecorView;

    public ImmersionBar(Activity activity) {
        mWindow = activity.getWindow();
        mDecorView = (ViewGroup) mWindow.getDecorView();
    }

    public static ImmersionBar with(Activity activity) {
        return new ImmersionBar(activity);
    }

    public void setup() {
        //防止系统栏隐藏时内容区域大小发生变化
        int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            uiFlags = initBarAboveLOLLIPOP(uiFlags);
        } else {

        }
        mDecorView.setSystemUiVisibility(uiFlags);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private int initBarAboveLOLLIPOP(int uiFlags) {
        uiFlags |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        mWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //需要设置这个才能设置状态栏和导航栏颜色
        mWindow.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        setupStatusBarColor(Color.WHITE);
        //设置导航栏颜色
        setupNavigationBarColor(Color.GRAY);
        //
        uiFlags = setupStatusBarDark(uiFlags);
        return uiFlags;
    }

    private void setupStatusBarColor(int color){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWindow.setStatusBarColor(color);
        }
    }

    private void setupNavigationBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWindow.setNavigationBarColor(color);
        }
    }

    private int setupStatusBarDark(int uiFlags) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return uiFlags | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        } else {
            return uiFlags;
        }
    }

}
