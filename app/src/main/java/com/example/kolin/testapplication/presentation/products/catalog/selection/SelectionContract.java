package com.example.kolin.testapplication.presentation.products.catalog.selection;

import com.example.kolin.testapplication.domain.Restaurant;

import java.util.List;

/**
 * Created by kolin on 06.09.2016.
 */

public interface SelectionContract {

    interface View {
        void showLoadedRestaurants(List<Restaurant> restaurants);
    }

}
