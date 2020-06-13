package com.handy.module4android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.handy.module.core.RxHttp;
import com.handy.module.request.IRequestParam;
import com.handy.module.request.JsonRequest;
import com.handy.module.utils.LogUtils;

/**
 * @author: handy
 * @date: 2020-06-11
 * @description:
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
        RxHttp.httpGet(null, null);

        Hello hello = new Hello();
        hello.name = "name";
        IRequestParam request = new JsonRequest(hello);
        LogUtils.d(TAG, "request: " + request.convert());
    }
}
