package com.handy.module.life;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;

/**
 * @author: handy
 */
public final class LifeObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable, LifecycleEventObserver {

    private Observer<? super T> downStream;
    private LifecycleOwner lifecycleOwner;

    LifeObserver(Observer<? super T> downstream,LifecycleOwner lifecycleOwner) {
        this.downStream = downstream;
        this.lifecycleOwner = lifecycleOwner;
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.setOnce(this, disposable)) {
            try {
                lifecycleOwner.getLifecycle().addObserver(this);
                downStream.onSubscribe(disposable);
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                disposable.dispose();
                onError(ex);
            }
        }
    }

    @Override
    public void onNext(T t) {
        if (isDisposed()) return;
        try {
            downStream.onNext(t);
        } catch (Throwable e) {
            Exceptions.throwIfFatal(e);
            get().dispose();
            onError(e);
        }
    }

    @Override
    public void onError(Throwable t) {
        if (isDisposed()) {
            RxJavaPlugins.onError(t);
            return;
        }
        lazySet(DisposableHelper.DISPOSED);
        try {
            removeObserver();
            downStream.onError(t);
        } catch (Throwable e) {
            Exceptions.throwIfFatal(e);
            RxJavaPlugins.onError(new CompositeException(t, e));
        }
    }

    @Override
    public void onComplete() {
        if (isDisposed()) return;
        lazySet(DisposableHelper.DISPOSED);
        try {
            removeObserver();
            downStream.onComplete();
        } catch (Throwable e) {
            Exceptions.throwIfFatal(e);
            RxJavaPlugins.onError(e);
        }
    }

    private void removeObserver(){

    }

    @Override
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }

    @Override
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        if(event == Lifecycle.Event.ON_DESTROY){
            dispose();
        }
    }

}
