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
import com.example.kolin.testapplication.presentation.products.catalog.foodlist.ListFragment;
import com.example.kolin.testapplication.presentation.products.catalog.selection.SelectionFragment;

import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private PagerAdapter pagerAdapter;

    private int extra;
    private String extraString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        Intent intent = getIntent();
        extra = intent.getIntExtra("NameCategory", 0);
        if (extra == 0){
            extraString = "Продукты";
        } else {
            extraString = "Рестораны";
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar_catalog);
        tabLayout = (TabLayout) findViewById(R.id.tabs_catalog);
        viewPager = (ViewPager) findViewById(R.id.view_pager_catalog);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setupPagerAdapter();
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupPagerAdapter() {
        if (pagerAdapter == null){
            pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        }

        pagerAdapter.addFragment(extraString, new SelectionFragment());
        pagerAdapter.addFragment("Список", new ListFragment());
    }

    static class PagerAdapter extends FragmentPagerAdapter {

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

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        public void addFragment(String title, Fragment fragment) {
            titleList.add(title);
            fragmentList.add(fragment);
        }
    }
}
