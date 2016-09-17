package com.example.kolin.testapplication.presentation.products.catalog;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.groups.GroupName;
import com.example.kolin.testapplication.presentation.products.catalog.favoritelist.FavoriteFragment;
import com.example.kolin.testapplication.presentation.products.catalog.selection.SelectionFragment;

import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends AppCompatActivity
        implements SelectionFragment.OnClickItemOnSelectionFragment {

    private Toolbar toolbar;
    private TabLayout tabs;
    private ViewPager viewPager;

    private int extra;

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
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public void clickItem(String itemName) {

    }

    public void setupAdapter(){
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment( FavoriteFragment.newInstance(), "ИЗБРАННОЕ");
        pagerAdapter.addFragment(SelectionFragment.newInstance(extra), GroupName.getCategoryById(extra));
        viewPager.setAdapter(pagerAdapter);
    }

    static class PagerAdapter extends FragmentPagerAdapter{

        private List<String> titleList = new ArrayList<>();
        private List<Fragment> fragmentList = new ArrayList<>();

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
        public void addFragment(Fragment fragment, String title){
            titleList.add(title);
            fragmentList.add(fragment);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }
}
