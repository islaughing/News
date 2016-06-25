package com.example.news.laughing.utils;

import android.util.Log;

import com.example.news.laughing.beans.NewsBean;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;


/**
 * Created by Administrator on 2016/6/8.
 */
public class JsonUtils {
    public static Gson gson = new Gson();
    public static <T> String toJson(T t) {
        return gson.toJson(t);
    }

    public static <T> T toObject(String json, Class<T> clazz) throws JsonSyntaxException {
        return gson.fromJson(json, clazz);
    }

    public static <T> T toObject(JsonObject jsonArray, Class<T> clazz) throws JsonSyntaxException{

        return gson.fromJson(jsonArray,clazz);

    }
    public static <T> T toObject(String jsonObject, Type type)throws JsonSyntaxException{
        return gson.fromJson(jsonObject,type);
    }


}
