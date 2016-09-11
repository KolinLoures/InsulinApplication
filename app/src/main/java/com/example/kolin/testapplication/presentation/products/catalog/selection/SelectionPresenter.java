package com.example.kolin.testapplication.presentation.products.catalog.selection;

import android.util.Log;

import com.example.kolin.testapplication.domain.ItemCategory;
import com.example.kolin.testapplication.domain.categories.Categories;
import com.example.kolin.testapplication.domain.interactor.DefaultSubscriber;
import com.example.kolin.testapplication.domain.interactor.GetCategoriesUC;
import com.example.kolin.testapplication.presentation.AbstractPresenter;

import java.util.List;

/**
 * Created by kolin on 06.09.2016.
 */

public class SelectionPresenter extends AbstractPresenter<SelectionContractView> {

    private static final String TAG = "SelectionPresenter";

    private GetCategoriesUC getCategoriesUC;

    @Categories.Category
    private String currentCategory;

    public SelectionPresenter() {
        getCategoriesUC = new GetCategoriesUC();
    }


    public void loadRestaurants(List<ItemCategory> itemCategories) {
        if (!isViewAttach()) {
            Log.e(TAG, "View was detach!");
            return;
        }
        getWeakReference().showLoadedRestaurants(itemCategories);
    }


    public void load() {
        showLoadingProgressBar();
        getCategoriesUC.setParameterCategoryName(currentCategory);
        getCategoriesUC.execute(new SelectionSubscriber());
    }

    private void showLoadingProgressBar() {
        getWeakReference().showLoadingProgress();
    }

    private void hideLoadingProgressBar() {
        getWeakReference().hideLoadingProgress();
    }


    private final class SelectionSubscriber extends DefaultSubscriber<List<ItemCategory>> {
        @Override
        public void onCompleted() {
            SelectionPresenter.this.hideLoadingProgressBar();
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(List<ItemCategory> list) {
            SelectionPresenter.this.loadRestaurants(list);
        }
    }

    public void unSubscribe() {
        getCategoriesUC.unsubscribe();
    }

    public void setCurrentCategory(String currentCategory) {
        this.currentCategory = currentCategory;
    }
}
