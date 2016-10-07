package com.example.kolin.testapplication.presentation.calculator.calculation.result;

import com.example.kolin.testapplication.domain.Food;

import java.util.List;

/**
 * Created by kolin on 07.10.2016.
 */

public interface ResultView {

    void showLoadedData(List<Food> foodList);
}
