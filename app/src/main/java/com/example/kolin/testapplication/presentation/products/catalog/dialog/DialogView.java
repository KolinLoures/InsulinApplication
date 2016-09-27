package com.example.kolin.testapplication.presentation.products.catalog.dialog;

import com.example.kolin.testapplication.domain.Food;

import java.util.List;

/**
 * Created by kolin on 22.09.2016.
 */

public interface DialogView {

    void showLoadedData(List<Food> foodList);
}
