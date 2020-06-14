package com.handy.module4android;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.handy.module.core.ModuleCore;
import com.handy.module.plugin.ShareService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainCore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onNetworkClick(View view) {
        ModuleCore.initMapper();
        Log.d(TAG, "" + ModuleCore.getService(ShareService.class).getName());
        Log.d(TAG, "" + ModuleCore.getService(ShareService.class).getCount(10, 50));
        Log.d(TAG, "" + ModuleCore.getService(ShareService.class).getNumber());
        NetworkActivity.intentStart(this);
    }
}
