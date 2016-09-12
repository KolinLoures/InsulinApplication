package com.example.kolin.testapplication.presentation.products.catalog.foodlist;

import com.example.kolin.testapplication.domain.Food;

import java.util.List;

/**
 * Created by kolin on 12.09.2016.
 */

public interface ListFoodView {
    void showLoadedFood(List<Food> listFood);
}
