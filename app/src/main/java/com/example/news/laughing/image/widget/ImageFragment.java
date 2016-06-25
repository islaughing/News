package com.example.news.laughing.image.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.news.R;
import com.example.news.laughing.beans.ImageBean;
import com.example.news.laughing.image.ImageRecycleAdapter;
import com.example.news.laughing.image.presenter.ImagerPresenter;
import com.example.news.laughing.image.view.IImageFragmentView;
import com.example.news.laughing.url.Urls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/17.
 */
public class ImageFragment extends Fragment implements IImageFragmentView, SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ImagerPresenter imagerPresenter;
    private List<ImageBean> data;
    private LinearLayoutManager layoutManager;
    ImageRecycleAdapter adapter;
    private int pageIndex = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_fragment, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.image_swipe);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.image_recycle);
        setRecycle(recyclerView);
        imagerPresenter = new ImagerPresenter(this);
        recyclerView.addOnScrollListener(onScrollListener);
        onRefresh();
        return view;
    }
    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener(){
       private int lastVisibleItem;
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if(newState == RecyclerView.SCROLL_STATE_IDLE &&lastVisibleItem + 1 == adapter.getItemCount()
                    &&adapter.isShowFooter()){
                imagerPresenter.loadImager(pageIndex);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = layoutManager.findLastVisibleItemPosition();
        }
    };
    private void setRecycle(RecyclerView recycle) {
        recycle.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recycle.setLayoutManager(layoutManager);
        recycle.setItemAnimator(new DefaultItemAnimator());
        adapter = new ImageRecycleAdapter(getActivity().getApplicationContext());
        recycle.setAdapter(adapter);
    }

    @Override
    public void getImagerList(List<ImageBean> imageBeanList) {
        adapter.isShowFooter(true);
        if (data == null) {
            data = new ArrayList<>();
        }
        data.addAll(imageBeanList);
        if(pageIndex == 1){
            adapter.setDate(data);
        }
        else {
            if(imageBeanList == null || imageBeanList.size() == 0){
                adapter.isShowFooter(false);
            }
            adapter.notifyDataSetChanged();
        }
        pageIndex += Urls.PLUSINDEX;

    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);

    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void showLoadFailMsg() {
        View view = getActivity() == null ? recyclerView.getRootView() : getActivity().findViewById(R.id.main_drawer);
        Snackbar.make(view, getString(R.string.fail), Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void onRefresh() {
        pageIndex =1;
        if(data != null){
            data.clear();
        }
        imagerPresenter.loadImager(pageIndex);
        }

}

