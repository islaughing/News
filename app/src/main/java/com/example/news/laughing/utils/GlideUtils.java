package com.example.news.laughing.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ant.liao.GifView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.ViewTarget;
import com.example.news.R;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrator on 2016/6/8.
 */
public class GlideUtils {
    private static ImageView imageView1;
    private static Context mContext;
    private static int mMaxWidth;
    private static int mMaxHeight;
    public static SimpleTarget<Bitmap> target = new SimpleTarget<Bitmap>() {

        @Override
        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
            mMaxWidth = ToolsUtil.getWidthInPx(mContext) - 20;
            mMaxHeight = ToolsUtil.getHeightInPx(mContext) - ToolsUtil.getStatusHeight(mContext) -
                    ToolsUtil.dip2px(mContext, 96);
            int h = resource.getHeight();
            int w = resource.getWidth();
            float scale = (float)w / (float) mMaxWidth;
            int height = (int)(h / scale);
            if(height > mMaxHeight) {
                height = mMaxHeight;
            }
            imageView1.setLayoutParams(new LinearLayout.LayoutParams(mMaxWidth, height));
            imageView1.setImageBitmap(resource);
        }
    };

    public static void loadImager(Context context, ImageView imageView, String url) {
        if(imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_image_loadfail).centerCrop().into(imageView);

    }
    public static void loadImage(Context context, ImageView imageView, String url) {
        imageView1 = imageView;
        mContext = context;
        if(imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).asBitmap().placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_image_loadfail).centerCrop().into(target);
    }

}
