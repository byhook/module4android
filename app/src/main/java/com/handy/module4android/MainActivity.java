package com.handy.module4android;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.handy.module.core.ModuleCore;
import com.handy.module.pages.ImmersionActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainCore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onNetworkClick(View view) {
        ModuleCore.initMapper();
        NetworkActivity.intentStart(this);
    }

    public void onImmersionClick(View view) {
        ImmersionActivity.intentStart(this);
    }

    public void onPermissionClick(View view){
        PermissionActivity.intentStart(this);
    }

}
