package com.example.news.laughing.news.presenter;

import android.util.Log;

import com.example.news.laughing.news.model.INewsModel;
import com.example.news.laughing.news.model.NewsModel;
import com.example.news.laughing.news.view.INewsDatailView;
import com.example.news.laughing.url.Urls;

/**
 * Created by Administrator on 2016/6/11.
 */
public class NewsDatailPresenter implements INewsDatailPresenter ,NewsModel.OnloadNewsContentListener{
    private INewsDatailView newsDatailView;
    private INewsModel newsModel;
    public NewsDatailPresenter(INewsDatailView newsDatailView){
        this.newsDatailView = newsDatailView;
        newsModel = new NewsModel();
    }
    @Override
    public void loadDatail(final String url) {
        newsDatailView.showProgress();
        newsModel.loadNewsContent(getDatailUrl(url),this);

    }
    public String getDatailUrl(final String link){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Urls.NEWSDATAILHOST).append("url").append("=").append(link).append("&").
                append("showapi_appid").append("=").append(Urls.showapi_appid).append("&").
                append("showapi_sign").append("=").append(Urls.showapi_sign);
        return stringBuffer.toString();

    }

    @Override
    public void onSuccess(String content) {
        if(content != null){
            newsDatailView.showNewsDatailContent(content);
        }
        newsDatailView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {
            newsDatailView.hideProgress();
        newsDatailView.showNewsDatailContent(msg);
    }
}
