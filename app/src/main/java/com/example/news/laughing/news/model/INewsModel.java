package com.example.news.laughing.news.model;

/**
 * Created by Administrator on 2016/6/9.
 */
public interface INewsModel {
    void loadNews(String url, NewsModel.OnLoadNewsListListener listListener);


    void loadNewsContent( String url, NewsModel.OnloadNewsContentListener contentListener);
}