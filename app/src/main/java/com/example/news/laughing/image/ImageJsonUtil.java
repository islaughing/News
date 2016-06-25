package com.example.news.laughing.image;

import android.util.Log;

import com.example.news.laughing.beans.ImageBean;
import com.example.news.laughing.beans.NewsBean;
import com.example.news.laughing.utils.JsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/17.
 */
public class ImageJsonUtil {
    public static List<ImageBean> readImageJson(String response){
        List<ImageBean> list = new ArrayList<>();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(response).getAsJsonObject();
        JsonElement jsonElement = jsonObject.get("showapi_res_body");
        JsonObject jsonObject1 = jsonElement.getAsJsonObject();
        JsonElement jsonElement2 = jsonObject1.get("contentlist");
        if(jsonElement2.isJsonNull()){
            return null;
        }
        JsonArray jsonArray = jsonElement2.getAsJsonArray();
        for(int i = 0; i < jsonArray.size();i++){
            JsonObject object = jsonArray.get(i).getAsJsonObject();
            String imagUrl = object.get("img").getAsString();
            if(!imagUrl.endsWith("gif") && !imagUrl.endsWith("png")
                    && !imagUrl.endsWith("jpg")){
                continue;
            }
            ImageBean imageBean = JsonUtils.toObject(object, ImageBean.class);
            list.add(imageBean);
        }
        return list;
    }
}
