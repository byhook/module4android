package com.handy.module4android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import com.handy.module.core.NetCore;
import com.handy.module.utils.ArchUtils;
import com.handy.module.utils.LogUtils;
import com.handy.module.utils.ProcessUtils;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onNetworkClick(View view) {
        MusicPlayActivity.intentStart(this);
    }
}
