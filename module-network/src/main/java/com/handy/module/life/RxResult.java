package com.handy.module.life;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.handy.module.helper.RxHelper;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.functions.Functions;
import io.reactivex.rxjava3.internal.observers.LambdaObserver;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;


/**
 * @author: handy
 * @date: 2020-06-11
 * @description:
 */
public class RxResult<T> extends RxSource<Observer<? super T>> {

    private static final String TAG = "RxResult";
    private Observable<T> upStream;
    private Disposable disposable;
    private LifecycleOwner lifecycleOwner;

    public RxResult(Observable<T> upStream, LifecycleOwner lifecycleOwner) {
        this.upStream = upStream;
        this.lifecycleOwner = lifecycleOwner;
    }

    @Override
    public final Disposable subscribe() {
        return subscribe(Functions.emptyConsumer(), Functions.ON_ERROR_MISSING, Functions.EMPTY_ACTION);
    }

    public final Disposable subscribe(@NonNull Consumer<? super T> onNext) {
        return subscribe(onNext, Functions.ON_ERROR_MISSING, Functions.EMPTY_ACTION);
    }

    public final Disposable subscribe(@NonNull Consumer<? super T> onNext, @NonNull Consumer<? super Throwable> onError) {
        return subscribe(onNext, onError, Functions.EMPTY_ACTION);
    }

    @NonNull
    public final Disposable subscribe(@NonNull Consumer<? super T> onNext, @NonNull Consumer<? super Throwable> onError,
                                      @NonNull Action onComplete) {
        RxHelper.requireNonNull(onNext, "onNext is null");
        RxHelper.requireNonNull(onError, "onError is null");
        RxHelper.requireNonNull(onComplete, "onComplete is null");

        LambdaObserver<T> ls = new LambdaObserver<>(onNext, onError, onComplete, Functions.emptyConsumer());

        subscribe(ls);

        return ls;
    }

    @Override
    public void subscribe(Observer<? super T> observer) {
        RxHelper.requireNonNull(observer, "observer is null");
        try {
            observer = RxJavaPlugins.onSubscribe(upStream, observer);

            RxHelper.requireNonNull(observer, "The RxJavaPlugins.onSubscribe hook returned a null Observer. Please change the handler provided to RxJavaPlugins.setOnObservableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");

            subscribeActual(observer);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable e) {
            Exceptions.throwIfFatal(e);
            // can't call onError because no way to know if a Disposable has been set or not
            // can't call onSubscribe because the call might have set a Subscription already
            RxJavaPlugins.onError(e);

            NullPointerException npe = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
            npe.initCause(e);
            throw npe;
        }
    }

    private void subscribeActual(Observer<? super T> observer) {
        Observable<T> upStream = this.upStream;
        upStream.onTerminateDetach().subscribe(new LifeObserver<>(observer, lifecycleOwner));
    }

}
