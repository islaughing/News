package com.example.news.laughing.news.view;

import com.example.news.laughing.beans.NewsBean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/9.
 */
public interface INewsView {
    void addNews(List<NewsBean> list);
    void showProgress();
    void hideProgress();
    void showLoadFailMsg();
}
