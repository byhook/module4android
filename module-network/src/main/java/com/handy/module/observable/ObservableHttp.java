package com.handy.module.observable;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;

/**
 * @anchor: handy
 * @date: 2020-06-13
 * @description:
 */
public class ObservableHttp<T> extends Observable<T> {

    @Override
    protected void subscribeActual(@NonNull Observer<? super T> observer) {

    }

}
