package com.example.kolin.testapplication.presentation.products.catalog;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.groups.GroupName;
import com.example.kolin.testapplication.presentation.calculator.calculation.CalculationActivity;
import com.example.kolin.testapplication.presentation.common.weightdialog.WeightFragment;
import com.example.kolin.testapplication.presentation.products.catalog.dialog.DialogFragment;
import com.example.kolin.testapplication.presentation.products.catalog.favoritelist.FavoriteFragment;
import com.example.kolin.testapplication.presentation.products.catalog.foodlist.ListFoodFragment;
import com.example.kolin.testapplication.presentation.products.catalog.selection.SelectionFragment;

import java.util.List;

public class CatalogActivity extends AppCompatActivity implements
        SelectionFragment.OnClickItemOnSelectionFragment,
        DialogFragment.OnClickCalculateButtonListener,
        ListFoodFragment.OnClickAddFoodToCalculator,
        FavoriteFragment.OnClickAddFavoriteFoodToCalculator{

    private Toolbar toolbar;
    private TabLayout tabs;
    private ViewPager viewPager;

    private MenuItem searchItem;
    private SearchView searchView;

    private int extra;
    private PagerAdapter pagerAdapter;


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
    public void clickItemSelectionFragment(String itemName, String title) {
        if (pagerAdapter.getItem(2) == null) {
            pagerAdapter.addFragment(ListFoodFragment.newInstance(itemName), title, 2);
        } else {
            Bundle args = new Bundle();
            args.putString("itemName", itemName);
            pagerAdapter.setTitleToPosition(title, 2);
            pagerAdapter.setBundleToUpdate(args);
            pagerAdapter.notifyDataSetChanged();
        }
        viewPager.setCurrentItem(pagerAdapter.getCount() - 1);
    }

    public void setupAdapter() {
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(FavoriteFragment.newInstance(), "ИЗБРАННОЕ", 0);
        pagerAdapter.addFragment(SelectionFragment.newInstance(extra), GroupName.getCategoryById(extra), 1);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(pagerAdapter.getCount() - 1);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalog, menu);

        searchItem = menu.findItem(R.id.item_search_catalog);
        SearchManager searchManager =
                (SearchManager) CatalogActivity.this.getSystemService(Context.SEARCH_SERVICE);

        searchView = (SearchView) searchItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(CatalogActivity.this.getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_catalog_added_products:
                showDialog();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogFragment dialogFragment = DialogFragment.newInstance("Список расчета");
        dialogFragment.show(fragmentManager, DialogFragment.class.getSimpleName());
    }

    @Override
    public void onClickCalculateBtn(List<Food> foodList) {
        Intent intent = new Intent(getApplicationContext(), CalculationActivity.class);
//        intent.putExtra("list", (Serializable) foodList);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClickAddFood(Food food) {
        openWeightDialog(food);
    }

    @Override
    public void onClickAddFavoriteToCalculator(Food food) {
        openWeightDialog(food);
    }

    private void openWeightDialog(Food food){
        WeightFragment weightFragment = WeightFragment.newInstance(food);
        weightFragment.show(getSupportFragmentManager(), WeightFragment.class.getSimpleName());
    }
}
