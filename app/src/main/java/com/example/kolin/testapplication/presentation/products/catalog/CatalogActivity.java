package com.example.kolin.testapplication.presentation.products.catalog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.groups.GroupName;
import com.example.kolin.testapplication.presentation.products.catalog.foodlist.ListFoodFragment;
import com.example.kolin.testapplication.presentation.products.catalog.selection.SelectionFragment;

public class CatalogActivity extends AppCompatActivity
        implements SelectionFragment.OnClickItemOnSelectionFragment {

    private Toolbar toolbar;

    private int extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        toolbar = (Toolbar) findViewById(R.id.toolbar_catalog);


        Intent intent = getIntent();
        extra = intent.getIntExtra("NameCategory", 0);

        toolbar.setTitle(GroupName.getCategoryById(extra));
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.catalog_fragment_container, SelectionFragment.newInstance(extra));
        fragmentTransaction.commit();
    }

    @Override
    public void clickItem(String itemName) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.catalog_fragment_container, ListFoodFragment.newInstance(itemName));
        fragmentTransaction.commit();
    }
}
