package com.example.news.laughing.image.model;

import android.util.Log;

import com.example.news.laughing.beans.ImageBean;
import com.example.news.laughing.image.ImageJsonUtil;
import com.example.news.laughing.url.Urls;
import com.example.news.laughing.utils.OkHttpUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/6/17.
 */
public class ImagerModel implements IImagerModel {

    @Override
    public void loadImages(final String url,final LoadImageResult loadImageResult) {
        OkHttpUtils.ResultCallback<String> callback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onFailure(Exception e) {
                loadImageResult.onFailure();

            }

            @Override
            public void onSuccess(String response) {
                List<ImageBean> list = ImageJsonUtil.readImageJson(response);
                loadImageResult.onSuccess(list);
            }
        };
        OkHttpUtils.get(url,callback);

    }

    public interface LoadImageResult{
        void onSuccess(List<ImageBean> list);
        void onFailure();
    }
}
