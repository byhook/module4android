package com.handy.module.converter;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @author: handy
 */
public class GsonConverter implements IConverter {

    @Override
    public <T> T convert(ResponseBody body, Type type) throws IOException {
        return null;
    }

    @Override
    public <T> RequestBody convert(T value) throws IOException {
        return null;
    }

}
