package com.example.news.laughing.news.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.news.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/7.
 */
public class NewsFragment extends Fragment{
    private TabLayout tabLayout;
    public static final int LOL = 0;
    public static final int SPORTS = 1;
    public static final int SCHOOL = 2;
    public static final int LOVE = 3;
    private ViewPager viewpager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment,container,false);
        viewpager = (ViewPager)view.findViewById(R.id.news_fragment_viewPager);
        viewpager.setOffscreenPageLimit(3);
        setViewPagerAdapter(viewpager);
        tabLayout = (TabLayout)view.findViewById(R.id.news_fragment_table);
        tabLayout.addTab(tabLayout.newTab().setText("LOL"));
        tabLayout.addTab(tabLayout.newTab().setText("体育"));
        tabLayout.addTab(tabLayout.newTab().setText("校园"));
        tabLayout.addTab(tabLayout.newTab().setText("明星"));
        tabLayout.setupWithViewPager(viewpager);
        return view;
    }

    private void setViewPagerAdapter(ViewPager viewpager) {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager());
        fragmentAdapter.addFragment(NewsListFragment.newsInstance(LOL),"LOL");
        fragmentAdapter.addFragment(NewsListFragment.newsInstance(SPORTS),"体育");
        fragmentAdapter.addFragment(NewsListFragment.newsInstance(SCHOOL),"校园");
        fragmentAdapter.addFragment(NewsListFragment.newsInstance(LOVE),"明星");
        viewpager.setAdapter(fragmentAdapter);

    }

    public class FragmentAdapter extends FragmentPagerAdapter{
        private List<Fragment> fragmentList = new ArrayList<>();
        private List<String> stringList = new ArrayList<>();

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }
        public void addFragment(Fragment fragment, String tab){
            fragmentList.add(fragment);
            stringList.add(tab);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return stringList.get(position);
        }
    }
}
