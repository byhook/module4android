package com.handy.module.converter;


import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @author: handy
 * @date: 2020-06-09
 * @description:
 */
public interface IConverter {

    <T> T convert(ResponseBody body, Type type) throws IOException;

    <T> RequestBody convert(T value) throws IOException;

}
