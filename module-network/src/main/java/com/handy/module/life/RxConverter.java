package com.handy.module.life;

import io.reactivex.rxjava3.core.ObservableConverter;

/**
 * @author: handy
 */
public interface RxConverter<T> extends ObservableConverter<T, RxResult<T>> {


}
