package com.example.news.laughing.news.presenter;

import android.content.Context;
import android.util.Log;

import com.example.news.laughing.beans.NewsBean;
import com.example.news.laughing.news.model.INewsModel;
import com.example.news.laughing.news.model.NewsModel;
import com.example.news.laughing.news.view.INewsView;
import com.example.news.laughing.news.widget.NewsFragment;
import com.example.news.laughing.url.Urls;
import com.example.news.laughing.utils.AnalysisSign;
import com.example.news.laughing.utils.ToolsUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/6/9.
 */
public class NewsPresenter implements INewsPresenter, NewsModel.OnLoadNewsListListener {
    private INewsModel newsModel;
    private INewsView newsView;
    private Context context;


    public NewsPresenter(Context context, INewsView newsView) {
        this.context = context;
        this.newsView = newsView;
        newsModel = new NewsModel();
    }

    @Override
    public void load(final int type,final int page) {
     /*   if(!ToolsUtil.isNetworkAvailable(context)){
            newsView.showLoadFailMsg();
            return;
        }*/
        if(page == 1){

            newsView.showProgress();
        }

        newsModel.loadNews(getUrl(type,page),this);

    }
    public String getUrl(int type,int page){
        StringBuffer stringBuffer = new StringBuffer();
        String sign;
        String title = null;
        switch (type){
            case NewsFragment.LOL:
                title = Urls.LOLNEWSTITLE;
                break;
            case  NewsFragment.LOVE:
                title = Urls.LOVETITLE;
                break;
            case NewsFragment.SCHOOL:
                title = Urls.SCHOOLTITLE;
                break;
            case NewsFragment.SPORTS:
                title = Urls.SPORTSNEWSTITLE;
                break;
            default:
                title = Urls.LOLNEWSTITLE;
                break;
        }
        sign = AnalysisSign.getsign(title,page,Urls.showapi_appid,Urls.showapi_sign);
        stringBuffer.append(Urls.HOST).append("?").append(Urls.TITLEKEY).append("=").
                append(title).append("&").append(Urls.PAGEKEY).append("=").
                append(page).append("&").append(Urls.APPIDAPIEKEY).append("=").
                append(Urls.showapi_appid).append("&").append(Urls.SIGNKEY).append("=").append(sign);
        return stringBuffer.toString();
    }

    @Override
    public void onSuccess(List<NewsBean> list) {
        newsView.hideProgress();
        newsView.addNews(list);

    }

    @Override
    public void onFailure(String msg, Exception e) {
        newsView.hideProgress();
        newsView.showLoadFailMsg();

    }
}
