package com.handy.module.core;

import androidx.lifecycle.LifecycleOwner;

import com.handy.module.life.RxLife;
import com.handy.module.request.IRequestParam;
import com.handy.module.response.IResponse;
import com.handy.module.ssl.SSLFactory;
import com.handy.module.utils.LogUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * @author: handy
 */
public class RxHttp {

    private static final String TAG = "NetCore";

    private static OkHttpClient okHttpClient;

    static {
        SSLFactory.SSLParams sslParams = SSLFactory.getSslSocketFactory();
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager) //添加信任证书
                .hostnameVerifier((hostname, session) -> true)
                .build();
    }

    public static void httpGet(IRequestParam requestParam, IResponse response) {
        Request.Builder builder = new Request.Builder();
        builder.url("https://www.baidu.com");
        okHttpClient.newCall(builder.build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                LogUtils.d(TAG, "onFailure");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                LogUtils.d(TAG, "onResponse " + response);
            }
        });
    }

    public static <T> Observable<T> httpPost(IRequestParam request) {
        return null;
    }

    public static void httpPost(LifecycleOwner lifecycleOwner) {
        Observable.interval(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Throwable {
                        LogUtils.d(TAG, "doOnDispose");
                    }
                })
                .to(RxLife.observe(lifecycleOwner))
                .subscribeWith(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Long value) {
                        LogUtils.d(TAG, "onNext " + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(TAG, "onError");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "onComplete");
                    }
                });
    }

}
