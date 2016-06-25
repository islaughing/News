package com.example.news.laughing.news;


import com.example.news.laughing.beans.NewsBean;
import com.example.news.laughing.utils.JsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/9.
 */
public class NewsJsonUtil {
    public static List<NewsBean> jsonToNewsBean(String response){
        List<NewsBean> list = new ArrayList<>();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(response).getAsJsonObject();
        JsonElement jsonElement = jsonObject.get("showapi_res_body");
        JsonObject jsonObject1 = jsonElement.getAsJsonObject();
        JsonElement jsonElement1 = jsonObject1.get("pagebean");
        JsonObject jsonObject2 = jsonElement1.getAsJsonObject();
        JsonElement jsonElement2 = jsonObject2.get("contentlist");
        if(jsonElement2.isJsonNull()){
            return null;
        }
        JsonArray jsonArray = jsonElement2.getAsJsonArray();
        for(int i = 0; i < jsonArray.size(); i++){
            JsonObject jsobject = jsonArray.get(i).getAsJsonObject();
            JsonArray jsonArray1 = jsobject.get( "imageurls").getAsJsonArray();
            if(jsonArray1.size() == 0){
                continue;
            }
            jsobject.remove("allList");
            jsobject.remove("imageurls");
            JsonObject jsonObject3 = jsonArray1.get(0).getAsJsonObject();
            String imagerUrl = jsonObject3.get("url").getAsString();
            NewsBean newsBean = JsonUtils.toObject(jsobject,NewsBean.class);
            newsBean.setUrl(imagerUrl);
            list.add(newsBean);
        }
        return list;
    }
    public static String getContent(String response){
        JsonParser jp = new JsonParser();
        JsonObject jo = jp.parse(response).getAsJsonObject();
        String content = jo.get("showapi_res_body").getAsJsonObject().get("html").getAsString();

        return content;
    }

}
