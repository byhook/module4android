package io.github.byhook.module.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author: handy
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
