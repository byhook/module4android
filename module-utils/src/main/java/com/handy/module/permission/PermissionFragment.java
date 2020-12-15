package com.handy.module.permission;

import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.handy.module.utils.LogUtils;

/**
 * date: 2020-12-14
 * description:
 */
public class PermissionFragment extends Fragment {

    private static final String TAG = "PermissionFragment";
    private static final String EXTRA_REQUEST_CODE = "EXTRA_REQUEST_CODE";
    private static final String EXTRA_PERMISSIONS = "EXTRA_PERMISSIONS";

    private OnPermissionCallback mOnPermissionCallback;

    private String[] mRequestPermissions;
    private int mRequestCode;

    private PermissionFragment() {

    }

    public static PermissionFragment newInstance(String[] permissions, int requestCode) {
        Bundle args = new Bundle();
        args.putStringArray(EXTRA_PERMISSIONS, permissions);
        args.putInt(EXTRA_REQUEST_CODE, requestCode);
        PermissionFragment fragment = new PermissionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void bindOnPermissionCallback(OnPermissionCallback onPermissionCallback) {
        this.mOnPermissionCallback = onPermissionCallback;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(TAG, "onCreate");
        setupPermission();
    }

    private void setupPermission() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            mRequestPermissions = arguments.getStringArray(EXTRA_PERMISSIONS);
            mRequestCode = arguments.getInt(EXTRA_REQUEST_CODE);
            requestPermissions(mRequestPermissions, mRequestCode);
        } else if(mOnPermissionCallback != null){
            mOnPermissionCallback.onRequestPermissionFailed(null,-1);
            recycleFragmentComplete();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LogUtils.d(TAG, "onRequestPermissionsResult");
        if (mOnPermissionCallback != null && grantResults != null) {
            int gratedCount = 0;
            for (int ret : grantResults) {
                if (PackageManager.PERMISSION_GRANTED == ret) {
                    gratedCount++;
                }
            }
            if(gratedCount == grantResults.length){
                mOnPermissionCallback.onRequestPermissionSuccess(permissions, mRequestCode);
            } else {
                mOnPermissionCallback.onRequestPermissionFailed(permissions, mRequestCode);
            }
        }
        recycleFragmentComplete();
    }

    private void recycleFragmentComplete() {
        FragmentActivity hostActivity = getActivity();
        if (hostActivity != null && !hostActivity.isFinishing()) {
            hostActivity.getSupportFragmentManager().beginTransaction()
                    .remove(this)
                    .commitAllowingStateLoss();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG, "onDestroy");
    }

}
