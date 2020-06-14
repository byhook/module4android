package com.handy.module.life;

import androidx.lifecycle.LifecycleOwner;

import io.reactivex.rxjava3.core.Observable;

/**
 * @author: handy
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
