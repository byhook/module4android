package com.handy.module.helper;

/**
 * @author: handy
 * @date: 2020-06-12
 * @description:
 */
public class RxHelper {

    public static <T> T requireNonNull(T obj, String message) {
        if (obj == null)
            throw new NullPointerException(message);
        return obj;
    }

}
