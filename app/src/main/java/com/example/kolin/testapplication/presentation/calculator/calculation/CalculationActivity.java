package com.example.kolin.testapplication.presentation.calculator.calculation;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.presentation.calculator.calculation.list.ListCalculatorFragment;

public class CalculationActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);

        toolbar = (Toolbar) findViewById(R.id.toolbar_calculation);

        toolbar.setTitle(R.string.calculator);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container_calculation, ListCalculatorFragment.newInstance());
        fragmentTransaction.commit();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
