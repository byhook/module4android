package com.handy.module4android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.handy.module.core.NetCore;

/**
 * @author: handy
 * @date: 2020-06-11
 * @description:
 */
public class MusicPlayActivity extends AppCompatActivity {

    private static final String TAG = "MusicPlayActivity";

    public static void intentStart(Context context){
        Intent intent = new Intent(context,MusicPlayActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetCore.httpPost(this);
    }
}
