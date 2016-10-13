package com.example.kolin.testapplication.presentation.calculator;

import com.example.kolin.testapplication.domain.CalculatedFood;

import java.util.List;

/**
 * Created by kolin on 10.10.2016.
 */

public interface CalculatorView {

    void showLoadedData(List<CalculatedFood> list);

    void showSnackBar(String title);

    void openCalculator(Long id);
}
