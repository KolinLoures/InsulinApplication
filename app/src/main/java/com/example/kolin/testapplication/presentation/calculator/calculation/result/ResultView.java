package com.example.kolin.testapplication.presentation.calculator.calculation.result;

import com.example.kolin.testapplication.domain.CalculatedFood;
import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.VitalCharacteristic;

import java.util.List;

/**
 * Created by kolin on 07.10.2016.
 */

public interface ResultView {

    void setVitalCharacteristic(List<VitalCharacteristic> list);

    void showLoadedData(List<Food> foodList);

    void showComputeResult(CalculatedFood calculatedFood);

    void showSnackBar(String title);
}
