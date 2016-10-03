package com.example.kolin.testapplication.presentation.calculator.calculation.list;

import com.example.kolin.testapplication.domain.CalculatedFood;
import com.example.kolin.testapplication.domain.Food;

import java.util.List;

/**
 * Created by kolin on 03.10.2016.
 */

public interface ListCalculatorView {

    void showCalculationFood(List<Food> foodList);

    void showSnackBar(String title);

    void showComputeResult(CalculatedFood calculatedFood);
}
