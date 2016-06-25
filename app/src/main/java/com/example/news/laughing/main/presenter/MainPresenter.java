package com.example.news.laughing.main.presenter;

import com.example.news.R;
import com.example.news.laughing.main.view.IMainView;

/**
 * Created by Administrator on 2016/6/7.
 */
public class MainPresenter implements IMainPresenter {
    private IMainView iMainView;
    public MainPresenter(IMainView iMainView){
        this.iMainView = iMainView;
    };
    @Override
    public void switchTitle(int id) {
        switch (id){
            case R.id.main_item_news:
                iMainView.switchNews();
                break;
            case R.id.main_item_picture:
                iMainView.switchPicture();
                break;
            default: iMainView.switchNews(); break;
        }

    }
}
