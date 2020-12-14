package com.handy.module.permission;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.handy.module.utils.LogUtils;

/**
 * @date: 2020-12-14
 * @description:
 */
public class PermissionFragment extends Fragment {

    private static final String TAG = "PermissionFragment";
    private static final String EXTRA_REQUEST_CODE = "EXTRA_REQUEST_CODE";
    private static final String EXTRA_PERMISSIONS = "EXTRA_PERMISSIONS";

    private OnPermissionCallback mOnPermissionCallback;

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
            String[] permissions = arguments.getStringArray(EXTRA_PERMISSIONS);
            int requestCode = arguments.getInt(EXTRA_REQUEST_CODE);
            requestPermissions(permissions, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LogUtils.d(TAG, "onRequestPermissionsResult");
        if(mOnPermissionCallback != null){
            mOnPermissionCallback.onRequestPermissionSuccess(null,200);
        }
        recyclePermissionFragment();
    }

    private void recyclePermissionFragment() {
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
