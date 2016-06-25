package com.example.news.laughing.news.widget;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.news.R;
import com.example.news.laughing.beans.NewsBean;
import com.example.news.laughing.news.presenter.INewsDatailPresenter;
import com.example.news.laughing.news.presenter.NewsDatailPresenter;
import com.example.news.laughing.news.view.INewsDatailView;
import com.example.news.laughing.news.view.INewsView;
import com.example.news.laughing.utils.GlideUtils;
import com.example.news.laughing.utils.ToolsUtil;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.List;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;


/**
 * Created by Administrator on 2016/6/11.
 */
public class NewsDatailActivity extends SwipeBackActivity implements INewsDatailView {
    private NewsBean newsBean;
    private HtmlTextView htmlTextView;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private INewsDatailPresenter newsDatailPresenter;
    private SwipeBackLayout swipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_datail);
        toolbar = (Toolbar)findViewById(R.id.news_datail_tool);
        progressBar = (ProgressBar)findViewById(R.id.news_datail_pro);
        htmlTextView = (HtmlTextView)findViewById(R.id.news_datail_html);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        swipeBackLayout = getSwipeBackLayout();
        swipeBackLayout.setEdgeSize(ToolsUtil.getWidthInPx(this));
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        newsBean = (NewsBean)getIntent().getSerializableExtra("news");
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.news_datail_coll);
        collapsingToolbarLayout.setTitle(newsBean.getTitle());
        GlideUtils.loadImager(getApplicationContext(), (ImageView) findViewById(R.id.news_datail_image), newsBean.geturl());
        newsDatailPresenter = new NewsDatailPresenter(this);
        newsDatailPresenter.loadDatail(newsBean.getLink());
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void showNewsDatailContent(String content) {
        htmlTextView.setHtmlFromString(content,new HtmlTextView.LocalImageGetter());

    }
}
