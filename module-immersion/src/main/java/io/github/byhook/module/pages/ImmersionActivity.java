package io.github.byhook.module.pages;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.github.byhook.module.immersion.ImmersionBar;
import io.github.byhook.module.immersion.R;

/**
 * @author: handy
 * @date: 2020-06-26
 * @description:
 */
public class ImmersionActivity extends AppCompatActivity {

    public static void intentStart(Context context){
        Intent intent = new Intent(context,ImmersionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersion_layer);
    }

    private void setupImmersion(){
        ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
        ViewGroup rootView = decorView.findViewById(Window.ID_ANDROID_CONTENT);
        if (rootView != null) {
            rootView.setFitsSystemWindows(true);
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    public void onImmersionClick(View view) {
        ImmersionBar
                .with(this)
                .setup();
    }
}
