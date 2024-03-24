package io.github.byhook.module4android;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.github.byhook.module.permission.OnPermissionCallback;
import io.github.byhook.module.permission.PermissionUtils;
import io.github.byhook.module.utils.LogUtils;

import java.util.Arrays;

/**
 * @date: 2020-12-14
 * @description:
 */
public class PermissionActivity extends AppCompatActivity {

    private static final String TAG = "PermissionActivity";

    public static void intentStart(Context context) {
        Intent intent = new Intent(context, PermissionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionUtils.applyPermission(this, new String[]{Manifest.permission.CAMERA}, 200, new OnPermissionCallback() {
            @Override
            public void onRequestPermissionSuccess(String[] permissions, int requestCode) {
                LogUtils.d(TAG, "onRequestPermissionSuccess: " + Arrays.toString(permissions) + " requestCode: " + requestCode);
            }

            @Override
            public void onRequestPermissionFailed(String[] permissions, int requestCode) {
                LogUtils.d(TAG, "onRequestPermissionSuccess: " + Arrays.toString(permissions) + " requestCode: " + requestCode);
            }
        });
    }

}
