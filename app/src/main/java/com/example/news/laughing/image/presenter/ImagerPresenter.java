package com.example.news.laughing.image.presenter;

import android.util.Log;

import com.example.news.laughing.beans.ImageBean;
import com.example.news.laughing.image.model.IImagerModel;
import com.example.news.laughing.image.model.ImagerModel;
import com.example.news.laughing.image.view.IImageFragmentView;
import com.example.news.laughing.url.Urls;
import com.example.news.laughing.utils.AnalysisSign;

import java.util.List;

/**
 * Created by Administrator on 2016/6/17.
 */
public class ImagerPresenter implements IImagePresenter , ImagerModel.LoadImageResult {
    private IImageFragmentView fragmentView;
    private IImagerModel imagerModel;
    public ImagerPresenter(IImageFragmentView fragmentView) {
        this.fragmentView = fragmentView;
        imagerModel = new ImagerModel();
    }

    @Override
    public void loadImager(int page) {
        if(page == 1){
            fragmentView.showProgress();
        }
        imagerModel.loadImages(getUrl(page),this);
    }

    @Override
    public void onSuccess(List<ImageBean> list) {
        fragmentView.getImagerList(list);
        fragmentView.hideProgress();
    }

    @Override
    public void onFailure() {
        fragmentView.showLoadFailMsg();
    }
    public String getUrl(int page){
        String sign = AnalysisSign.getImageSign(page, Urls.showapi_appid, Urls.showapi_sign);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Urls.IMAGES_HOST).append(Urls.PAGEKEY).append("=").
                append(page).append("&").append(Urls.APPIDAPIEKEY).append("=").
                append(Urls.showapi_appid).append("&").append(Urls.SIGNKEY).append("=").append(sign);
        return stringBuffer.toString();
    }
}
