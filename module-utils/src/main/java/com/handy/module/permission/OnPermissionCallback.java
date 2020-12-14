package com.handy.module.permission;

/**
 * @date: 2020-12-14
 * @description:
 */
public interface OnPermissionCallback {

    void onRequestPermissionSuccess(String[] permissions, int requestCode);

    void onRequestPermissionFailed(String[] permissions, int requestCode);

}
