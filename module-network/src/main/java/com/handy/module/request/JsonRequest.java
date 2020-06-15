package com.handy.module.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author: handy
 */
public class JsonRequest<T> implements IRequestParam<String> {

    private static Gson gson = new GsonBuilder().create();

    private T jsonParam;

    public JsonRequest(T jsonParam) {
        this.jsonParam = jsonParam;
    }

    @Override
    public String convert() {
        return gson.toJson(jsonParam);
    }

}
