package com.handy.module.core;

import androidx.lifecycle.LifecycleOwner;

import com.handy.module.life.RxLife;
import com.handy.module.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * @author: handy
 * @date: 2020-06-09
 * @description:
 */
public class NetCore {

    private static final String TAG = "NetCore";

    public static void httpPost(LifecycleOwner lifecycleOwner){
        Observable.interval(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Throwable {
                        LogUtils.d(TAG,"doOnDispose");
                    }
                })
                .to(RxLife.observe(lifecycleOwner))
                .subscribeWith(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG,"onSubscribe");
                    }

                    @Override
                    public void onNext(Long value) {
                        LogUtils.d(TAG,"onNext " + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG,"onError");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG,"onComplete");
                    }
                });
    }

}
