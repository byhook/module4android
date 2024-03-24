package io.github.byhook.module.utils;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * @author: handy
 */
public class ArchUtils {

    public static int getArchType(Context context) {
        FileInputStream headInputStream = null;
        try {
            ClassLoader classLoader = context.getClassLoader();
            Method method = ClassLoader.class.getDeclaredMethod("findLibrary", String.class);
            Object result = method.invoke(classLoader, "art");
            File artFile = new File(String.valueOf(result));
            headInputStream = new FileInputStream(artFile);
            int length = headInputStream.available();
            if (length >= 20) {
                byte[] buffer = new byte[2];
                System.out.println(length);
                headInputStream.skip(18);
                headInputStream.read(buffer, 0, 2);
                int value = (buffer[1] << 8 | buffer[0] & 0xFF);
                return value;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeSafely(headInputStream);
        }
        return -1;
    }

}
