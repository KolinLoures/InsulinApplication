package com.example.kolin.testapplication.presentation.common.weightdialog;

import com.example.kolin.testapplication.domain.Food;

/**
 * Created by kolin on 04.10.2016.
 */

public interface WeightView {

    void showFoodAttribute(Food food);

    void showToast(String title);
}
