package com.example.news.laughing.news.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.news.R;
import com.example.news.laughing.beans.NewsBean;
import com.example.news.laughing.news.MyCustomDecoration;
import com.example.news.laughing.news.adapter.RecyclerCustonAdapter;
import com.example.news.laughing.news.presenter.INewsPresenter;
import com.example.news.laughing.news.presenter.NewsPresenter;
import com.example.news.laughing.news.view.INewsView;
import com.example.news.laughing.url.Urls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/9.
 */
public class NewsListFragment extends Fragment implements INewsView, SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private INewsPresenter newsPresenter;
    private List<NewsBean> newsBeanList;
    private RecyclerCustonAdapter recyclerCustonAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int mType = NewsFragment.LOL;
    private int pageIndex = 1;
    public static NewsListFragment newsInstance(int type){
        Bundle bundle = new Bundle();
        NewsListFragment newsListFragment = new NewsListFragment();
        bundle.putInt("type", type);
        newsListFragment.setArguments(bundle);
        return newsListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsPresenter = new NewsPresenter(getContext(),this);
        mType = getArguments().getInt("type");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_list_contents,container,false);
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.new_list_swipe);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorPrimaryLight, R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView)view.findViewById(R.id.news_list_recycle);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerCustonAdapter = new RecyclerCustonAdapter(getActivity().getApplicationContext());
        recyclerCustonAdapter.setOnItemClickListener(onItemClickListener);
        recyclerView.setAdapter(recyclerCustonAdapter);
        recyclerView.addItemDecoration(new MyCustomDecoration(getActivity(), MyCustomDecoration.VERTICAL));
        recyclerView.addOnScrollListener(onScrollListener);
        onRefresh();
        return view;
    }
    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener(){
       private  int lastVisibleItem;
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if(newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == recyclerCustonAdapter.getItemCount()
                    && recyclerCustonAdapter.isShowFooter()){
                newsPresenter.load(mType,pageIndex);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
        }
    };
    private RecyclerCustonAdapter.OnItemClickListener onItemClickListener = new RecyclerCustonAdapter.OnItemClickListener(){

        @Override
        public void onItemClick(View view, int postion) {
            NewsBean newsBean = recyclerCustonAdapter.getItem(postion);
            Intent intent = new Intent(getActivity(),NewsDatailActivity.class);
            intent.putExtra("news",newsBean);
            View v = (ImageView)view.findViewById(R.id.news_item_image);
            ActivityOptionsCompat option = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),v,"transition_news_image");
            ActivityCompat.startActivity(getActivity(),intent,option.toBundle());


        }
    };


    @Override
    public void addNews(List<NewsBean> list) {
        recyclerCustonAdapter.isShowFooter(true);
        if(newsBeanList == null){
            newsBeanList = new ArrayList<NewsBean>();
        }
        newsBeanList.addAll(list);
        if(pageIndex == 1){
            recyclerCustonAdapter.setNewsList(newsBeanList);
        }else{
            if(list == null || list.size() == 0){
                recyclerCustonAdapter.isShowFooter(false);
            }
            recyclerCustonAdapter.notifyDataSetChanged();
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
        if(pageIndex == 1){
            recyclerCustonAdapter.isShowFooter(false);
            recyclerCustonAdapter.notifyDataSetChanged();
        }
        View view = getActivity() == null ? recyclerView.getRootView() : getActivity().findViewById(R.id.main_drawer);
        Snackbar.make(view,"加载失败！",Snackbar.LENGTH_SHORT).show();

    }


    @Override
    public void onRefresh() {
        pageIndex = 1;
        if(newsBeanList != null){
            newsBeanList.clear();
        }
        newsPresenter.load(mType,pageIndex);
    }
}
