package com.example.kolin.testapplication.presentation.products.catalog.selection;

import com.example.kolin.testapplication.domain.ItemCategory;

import java.util.List;

/**
 * Created by kolin on 06.09.2016.
 */

public interface SelectionContractView {
    void showLoadedRestaurants(List<ItemCategory> itemCategories);

    void showLoadingProgress();

    void hideLoadingProgress();
}
