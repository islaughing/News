package com.example.news.laughing.news.model;

import android.util.Log;

import com.example.news.laughing.beans.NewsBean;
import com.example.news.laughing.news.NewsJsonUtil;
import com.example.news.laughing.url.Urls;
import com.example.news.laughing.utils.JsonUtils;
import com.example.news.laughing.utils.OkHttpUtils;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/9.
 */
public class NewsModel implements INewsModel {
    @Override
    public void loadNews(String url,final OnLoadNewsListListener listListener) {
        OkHttpUtils.ResultCallback<String> resultCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onFailure(Exception e) {
                listListener.onFailure("Load fail",e);

            }

            @Override
            public void onSuccess(String response) {
                List<NewsBean> list = NewsJsonUtil.jsonToNewsBean(response);
                listListener.onSuccess(list);

            }
        };
        OkHttpUtils.get(url, resultCallback);

    }

    @Override
    public void loadNewsContent(String url,final OnloadNewsContentListener contentListener) {
        OkHttpUtils.ResultCallback<String> callback = new OkHttpUtils.ResultCallback<String>(){
            @Override
            public void onFailure(Exception e) {
                contentListener.onFailure("加载失败",e);
            }

            @Override
            public void onSuccess(String response) {
                String res = NewsJsonUtil.getContent(response);
                contentListener.onSuccess(res);

            }
        };
        OkHttpUtils.get(url,callback);

    }


    public interface OnLoadNewsListListener {
        void onSuccess(List<NewsBean> list);
        void onFailure(String msg, Exception e);
    }
    public interface OnloadNewsContentListener{
        void onSuccess(String content);
        void onFailure(String msg,Exception e);
    }
}
