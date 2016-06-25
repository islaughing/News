package com.example.news.laughing.image.view;

import com.example.news.laughing.beans.ImageBean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/17.
 */
public interface IImageFragmentView {
    void getImagerList( List<ImageBean> imageBeanList);
    void showProgress();
    void hideProgress();
    void showLoadFailMsg();
}
