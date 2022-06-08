package com.handy.module4android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


/**
 * @author: handy
 */
public class NetworkActivity extends AppCompatActivity {

    private static final String TAG = "NetworkActivity";

    public static void intentStart(Context context) {
        Intent intent = new Intent(context, NetworkActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
