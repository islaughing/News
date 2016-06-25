package com.example.news.laughing.news;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2016/6/17.
 */
public class MyCustomDecoration extends RecyclerView.ItemDecoration {

    private Drawable drawable;
    public static final int VERTICAL = LinearLayoutManager.VERTICAL;
    public static final int HORIZONTAL = LinearLayoutManager.HORIZONTAL;
    public static final int [] ATTRS = new int[]{
        android.R.attr.listDivider
    };
    private int morientation;
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if(morientation == VERTICAL){
            outRect.set(0,0,0,drawable.getIntrinsicHeight());
        }else{
            outRect.set(0,0,drawable.getIntrinsicWidth(),0);
        }
    }

    public MyCustomDecoration(Context context, int orientation) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        drawable = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);

    }

    private void setOrientation(int orientation) {
        if(orientation != VERTICAL && orientation != HORIZONTAL){
            throw new IllegalArgumentException("选择方向错误");
        }
        morientation = orientation;

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if(morientation == VERTICAL){
           drawVertical(c,parent);
       }else{
           drawHorizontal(c,parent);
       }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int count = parent.getChildCount();
        for(int i = 0; i<count; i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + drawable.getIntrinsicHeight();
            drawable.setBounds(left,top,right,bottom);
            drawable.draw(c);
        }
    }


    private void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int count = parent.getChildCount();
        for(int i = 0;i < count; i++){
            final View child = parent.getChildAt(i);
            RecyclerView v = new RecyclerView(parent.getContext());
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + drawable.getIntrinsicWidth();
            drawable.setBounds(left,top,right,bottom);
            drawable.draw(c);
        }
    }
}
