package com.handy.module.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author: handy
 * @date: 2020-06-10
 * @description:
 */
public class IOUtils {

    public static void closeSafely(Closeable closeable){
        if(closeable!=null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
