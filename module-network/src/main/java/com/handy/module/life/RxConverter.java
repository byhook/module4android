package com.handy.module.life;

import io.reactivex.rxjava3.core.ObservableConverter;

/**
 * @author: handy
 * @date: 2020-06-11
 * @description:
 */
public interface RxConverter<T> extends ObservableConverter<T, RxResult<T>> {


}
