package com.handy.module.life;


import io.reactivex.rxjava3.disposables.Disposable;

/**
 * @author: handy
 * @date: 2020-06-11
 * @description:
 */
public abstract class RxSource<T> {

    public abstract Disposable subscribe();

    public abstract void subscribe(T observer);

    public <O extends T> O subscribeWith(O observer) {
        subscribe(observer);
        return observer;
    }

}
