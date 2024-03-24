package io.github.byhook.module.core;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: handy
 */
public class ModuleCore {

    private static final String TAG = "ModuleCore";
    /**
     * 缓存映射
     */
    private static Map<Class<?>, Class<?>> sPluginMappings = new HashMap<>();

    /**
     * 缓存类
     */
    private static final Map<Class<?>, Object> sSingletonCaches = new HashMap();

    private volatile static Application sApplication;

    private volatile static boolean sEnableLog;

    public static void init(Application application) {
        sApplication = application;
    }

    public static void enableLog(boolean enable) {
        sEnableLog = enable;
    }

    public static boolean isLogEnabled() {
        return sEnableLog;
    }

    public static Application getApplication() {
        if (sApplication == null) {
            throw new IllegalStateException("you must init first");
        }
        return sApplication;
    }

    public static Context getAppContext() {
        return getApplication().getApplicationContext();
    }

    public static Resources getResources() {
        return getAppContext().getResources();
    }

    public static void initMapper() {
        try {
            Class clazz = Class.forName("com.handy.modules.ModuleMapper");
            Object object = clazz.newInstance();
            Method method = clazz.getMethod("loadModuleMapper", Map.class);
            method.invoke(object, sPluginMappings);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(sPluginMappings);
    }

    public static <T> T getService(final Class<T> targetClazz) {
        if (!targetClazz.isInterface()) {
            throw new IllegalArgumentException("only accept interface: " + targetClazz);
        }
        return (T) Proxy.newProxyInstance(targetClazz.getClassLoader(), new Class<?>[]{targetClazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) {
                try {
                    return invokeProxy(targetClazz, method, args);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

    private static Object invokeProxy(final Class<?> targetClazz, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Object implClass;
        if ((implClass = sSingletonCaches.get(targetClazz)) == null) {
            Class<?> clazz = sPluginMappings.get(targetClazz);
            Log.d(TAG, "invokeProxy from mapper " + clazz + " method: " + method.getName());
            if (clazz != null) {
                implClass = clazz.newInstance();
                Log.d(TAG, "invokeProxy new " + implClass + " method: " + method.getName());
                sSingletonCaches.put(targetClazz, implClass);
            }
        }
        Log.d(TAG, "invokeProxy from cache " + implClass + " method: " + method.getName());
        if (implClass != null) {
            return method.invoke(implClass, args);
        } else if (method.getReturnType() == byte.class
                || method.getReturnType() == char.class
                || method.getReturnType() == short.class
                || method.getReturnType() == int.class
                || method.getReturnType() == long.class
                || method.getReturnType() == float.class
                || method.getReturnType() == double.class) {
            return 0;
        } else if (method.getReturnType() == boolean.class) {
            return false;
        } else {
            return null;
        }
    }

}
