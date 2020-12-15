package com.handy.module.permission;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

/**
 * date: 2020-12-14
 * description:
 */
public class PermissionUtils {

    private static final String TAG = "HidePermission";

    public static void applyPermission(FragmentActivity fragmentActivity, String[] permissions, int requestCode,OnPermissionCallback onPermissionCallback) {
        PermissionFragment permissionFragment = PermissionFragment.newInstance(permissions,requestCode);
        permissionFragment.bindOnPermissionCallback(onPermissionCallback);
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(permissionFragment, TAG)
                .commitAllowingStateLoss();
    }

    private static Fragment findPermissionFragment(FragmentActivity fragmentActivity) {
        if (fragmentActivity != null) {
            return fragmentActivity.getSupportFragmentManager().findFragmentByTag(TAG);
        }
        return null;
    }

}
