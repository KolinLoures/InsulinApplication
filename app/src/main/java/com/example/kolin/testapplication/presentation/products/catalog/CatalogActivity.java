package com.example.kolin.testapplication.presentation.products.catalog;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.groups.GroupName;
import com.example.kolin.testapplication.presentation.products.catalog.favoritelist.FavoriteFragment;
import com.example.kolin.testapplication.presentation.products.catalog.foodlist.ListFoodFragment;
import com.example.kolin.testapplication.presentation.products.catalog.selection.SelectionFragment;

import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends AppCompatActivity
        implements SelectionFragment.OnClickItemOnSelectionFragment {

    private Toolbar toolbar;
    private TabLayout tabs;
    private ViewPager viewPager;

    private int extra;
    private PagerAdapter pagerAdapter;
    private ViewPager.OnPageChangeListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        Intent intent = getIntent();
        extra = intent.getIntExtra("NameCategory", 0);


        toolbar = (Toolbar) findViewById(R.id.toolbar_catalog);
        tabs = (TabLayout) findViewById(R.id.tabs_catalog);
        viewPager = (ViewPager) findViewById(R.id.view_pager_catalog);

        toolbar.setTitle(GroupName.getCategoryById(extra));
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setupAdapter();
        setupViewPagerListener();
        tabs.setupWithViewPager(viewPager);
    }

    private void setupViewPagerListener() {
        listener = new ViewPager.OnPageChangeListener() {

            private int positionScrolledPage = 0;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
        viewPager.addOnPageChangeListener(listener);
    }


    @Override
    public void clickItemSelectionFragment(String itemName, String title) {
        pagerAdapter.addFragment(ListFoodFragment.newInstance(itemName), title, 2);
        viewPager.setCurrentItem(pagerAdapter.getCount() - 1);
    }

    public void setupAdapter() {
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(FavoriteFragment.newInstance(), "ИЗБРАННОЕ", 0);
        pagerAdapter.addFragment(SelectionFragment.newInstance(extra), GroupName.getCategoryById(extra), 1);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(pagerAdapter.getCount() - 1);
    }

    static class PagerAdapter extends FragmentStatePagerAdapter {

        private List<String> titleList = new ArrayList<>();
        private List<Fragment> fragmentList = new ArrayList<>();
        private static int position = 0;

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            if (object instanceof ListFoodFragment){
                return POSITION_NONE;
            }
            return super.getItemPosition(object);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title, int position) {
            if (fragment instanceof  ListFoodFragment){
                removeFragment(position);
            }
            titleList.add(position, title);
            fragmentList.add(position, fragment);
            notifyDataSetChanged();
        }

        public void removeFragment(int position) {
            if (fragmentList.size() > position) {
                titleList.remove(position);
                fragmentList.remove(position);
                notifyDataSetChanged();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            setPosition(position);
            return titleList.get(position);
        }

        private static void setPosition(int position) {
            PagerAdapter.position = position;
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        listener = null;
        viewPager.addOnPageChangeListener(listener);
    }

}
