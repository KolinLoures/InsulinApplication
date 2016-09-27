package com.example.kolin.testapplication.presentation.products.catalog.favoritelist;

import com.example.kolin.testapplication.domain.Food;

import java.util.List;

/**
 * Created by kolin on 18.09.2016.
 */

public interface FavoriteView {

    void showFavoriteFood(List<Food> foodList);

    void showSnackBar(String title);
}
