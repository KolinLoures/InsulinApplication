package com.example.kolin.testapplication.presentation.calculator.calculation;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.interactor.GetObservableCalculatedFoodUC;
import com.example.kolin.testapplication.presentation.calculator.calculation.list.ListCalculatorFragment;
import com.example.kolin.testapplication.presentation.calculator.calculation.result.ResultFragment;
import com.example.kolin.testapplication.presentation.common.weightdialog.WeightFragment;
import com.pixelcan.inkpageindicator.InkPageIndicator;

public class CalculationActivity extends AppCompatActivity implements
        ListCalculatorFragment.ListCalculationFragmentListener {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private CalcPagerAdapter pagerAdapter;
    private InkPageIndicator inkPageIndicator;

    private GetObservableCalculatedFoodUC getObservableCalculatedFoodUC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);

        getObservableCalculatedFoodUC = new GetObservableCalculatedFoodUC();

        toolbar = (Toolbar) findViewById(R.id.activity_calculation_toolbar);
        viewPager = (ViewPager) findViewById(R.id.activity_calculation_view_pager);
        inkPageIndicator = (InkPageIndicator) findViewById(R.id.activity_calculation_indicator);

        toolbar.setTitle(R.string.calculator);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        pagerAdapter = new CalcPagerAdapter(getSupportFragmentManager());
        setupPagerAdapter();
    }

    private void setupPagerAdapter() {
        pagerAdapter.addFragment(ListCalculatorFragment.newInstance((Long) getIntent().getSerializableExtra("id")));
        pagerAdapter.addFragment(ResultFragment.newInstance());
        viewPager.setAdapter(pagerAdapter);
        inkPageIndicator.setViewPager(viewPager);
    }


    @Override
    protected void onDestroy() {
//        getObservableCalculatedFoodUC.clearCalculationFood();
        super.onDestroy();
    }

    @Override
    public void onClickChangeFood(Food food) {
        WeightFragment weightFragment = WeightFragment.newInstance(food);
        weightFragment.show(getSupportFragmentManager(), WeightFragment.class.getSimpleName());
    }
}
