package com.handy.module.life;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;

import io.reactivex.rxjava3.core.Observable;

/**
 * @author: handy
 * @date: 2020-06-11
 * @description:
 */
public class RxLife {

    private static final String TAG = "RxLife";

    public static <T> RxConverter<T> observe(LifecycleOwner lifecycleOwner){
        return new RxConverter<T>() {
            @Override
            public RxResult<T> apply(Observable<T> upstream) {
                return new RxResult<>(upstream,lifecycleOwner);
            }
        };
    }


}
