package com.example.news.laughing.news.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.laughing.beans.NewsBean;
import com.example.news.laughing.utils.GlideUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/6/9.
 */
public class RecyclerCustonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTR = 1;
    private List<NewsBean> list;
    private boolean showFooter = true;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public RecyclerCustonAdapter(Context context) {
        this.context = context;
    }

    public void setNewsList(List<NewsBean> l){
        list = l;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(!showFooter){
            return TYPE_ITEM;
        }
        if(position + 1 == getItemCount()){
            return TYPE_FOOTR;
        }else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        int begin = showFooter?1:0;
        if(list == null){
            return begin;
        }
        return list.size() + begin;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycle_item,parent,false);
            MyHolder myHolder = new MyHolder(v);

            return myHolder;
        }else {
            View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.footer,null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }
    }
    public class FooterViewHolder extends RecyclerView.ViewHolder{
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(holder instanceof MyHolder){

            NewsBean newsBean = list.get(position);
            if(newsBean == null){
                return;
            }
            ((MyHolder) holder).titleTextView.setText(newsBean.getTitle());
            ((MyHolder) holder).textView.setText(newsBean.getDesc());
            GlideUtils.loadImager(context, ((MyHolder) holder).imageView,newsBean.geturl());

    }}


    public NewsBean getItem(int position){
        return list ==  null?null:list.get(position);
    }
    public void isShowFooter(boolean showFooter){this.showFooter = showFooter;}
    public boolean isShowFooter(){return this.showFooter;}
    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }
    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public  ImageView imageView;
        public  TextView titleTextView;
        public  TextView textView;
        public MyHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.news_item_image);
            titleTextView = (TextView)itemView.findViewById(R.id.news_item_title_text);
            textView = (TextView)itemView.findViewById(R.id.news_item_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(onItemClickListener != null){
                onItemClickListener.onItemClick(v,this.getPosition());
            }
        }
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int postion);
    }
}
