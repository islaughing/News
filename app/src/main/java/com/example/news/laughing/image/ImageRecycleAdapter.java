package com.example.news.laughing.image;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ant.liao.GifView;
import com.example.news.R;
import com.example.news.laughing.beans.ImageBean;
import com.example.news.laughing.image.view.IImageFragmentView;
import com.example.news.laughing.news.adapter.RecyclerCustonAdapter;
import com.example.news.laughing.utils.GlideUtils;
import com.example.news.laughing.utils.ToolsUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/6/17.
 */
public class ImageRecycleAdapter extends RecyclerView.Adapter<ViewHolder>{
    private List<ImageBean> beanList;
    private Context context;
    private int mMaxWidth;
    private int mMaxHeight;
    private boolean showFooter = true;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    public ImageRecycleAdapter(Context context) {
        this.context = context;
        mMaxWidth = ToolsUtil.getWidthInPx(context);
        mMaxHeight = ToolsUtil.getHeightInPx(context);
        ToolsUtil.dip2px(context,96);
    }

    @Override
    public int getItemViewType(int position) {
        if(!showFooter){
            return TYPE_ITEM;
        }
        if(position + 1 == getItemCount()){
            return TYPE_FOOTER;
        }
        else{
            return TYPE_ITEM;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
            MyHolder myHolder = new MyHolder(view);
            return myHolder;
        }else{
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer,parent,false);
            v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            FootViewHolder footViewHolder = new FootViewHolder(v);
            return footViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(holder instanceof MyHolder){
        ImageBean imageBean = beanList.get(position);
        if(imageBean == null){
            return ;
        }
            ((MyHolder)holder).textView.setText(imageBean.getTitle());
        GlideUtils.loadImage(context, ((MyHolder) holder).imageView, imageBean.getImg());
        }

    }

    public void setDate(List<ImageBean> list){
        beanList = list;
        notifyDataSetChanged();
    }

    public void isShowFooter(boolean showFooter){this.showFooter = showFooter;}
    public boolean isShowFooter(){return this.showFooter;}

    @Override
    public int getItemCount() {
        int begin = showFooter?1:0;
        if(beanList == null){
            return 0;
        }
        return beanList.size() + begin;
    }
    public class FootViewHolder extends RecyclerView.ViewHolder{
        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;
        public MyHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.image_item_text);
            imageView = (ImageView)itemView.findViewById(R.id.iamge_item_image);
        }
    }
}
