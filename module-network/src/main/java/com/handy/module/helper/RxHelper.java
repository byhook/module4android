package com.handy.module.helper;

/**
 * @author: handy
 */
public class RxHelper {

    public static <T> T requireNonNull(T obj, String message) {
        if (obj == null)
            throw new NullPointerException(message);
        return obj;
    }

}
