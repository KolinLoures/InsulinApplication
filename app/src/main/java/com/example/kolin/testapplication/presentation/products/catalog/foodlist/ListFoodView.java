package com.example.kolin.testapplication.presentation.products.catalog.foodlist;

import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.FoodCategory;

import java.util.HashMap;
import java.util.List;

/**
 * Created by kolin on 12.09.2016.
 */

public interface ListFoodView {
    void showLoadedFood(HashMap<FoodCategory, List<Food>> foodCategoryListHashMap);

    void showSnackBar(String title);

    void clearAll();

    void showCheckedFood(List<Food> checkedFood);
}
