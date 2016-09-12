package com.example.kolin.testapplication.presentation.products.catalog.selection;

import android.util.Log;

import com.example.kolin.testapplication.domain.ItemOfGroup;
import com.example.kolin.testapplication.domain.groups.Group;
import com.example.kolin.testapplication.domain.interactor.DefaultSubscriber;
import com.example.kolin.testapplication.domain.interactor.GetItemsOfGroupUC;
import com.example.kolin.testapplication.presentation.AbstractPresenter;

import java.util.List;

/**
 * Created by kolin on 06.09.2016.
 */

public class SelectionPresenter extends AbstractPresenter<SelectionContractView> {

    private static final String TAG = "SelectionPresenter";

    private GetItemsOfGroupUC getItemsOfGroupUC;

    @Group.GroupAllFood
    private String currentCategory;

    public SelectionPresenter() {
        getItemsOfGroupUC = new GetItemsOfGroupUC();
    }


    public void loadRestaurants(List<ItemOfGroup> itemCategories) {
        if (!isViewAttach()) {
            Log.e(TAG, "View was detach!");
            return;
        }
        getWeakReference().showLoadedRestaurants(itemCategories);
    }


    public void load() {
        showLoadingProgressBar();
        getItemsOfGroupUC.setParameterCategoryName(currentCategory);
        getItemsOfGroupUC.execute(new SelectionSubscriber());
    }

    private void showLoadingProgressBar() {
        getWeakReference().showLoadingProgress();
    }

    private void hideLoadingProgressBar() {
        getWeakReference().hideLoadingProgress();
    }


    private final class SelectionSubscriber extends DefaultSubscriber<List<ItemOfGroup>> {
        @Override
        public void onCompleted() {
            SelectionPresenter.this.hideLoadingProgressBar();
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(List<ItemOfGroup> list) {
            SelectionPresenter.this.loadRestaurants(list);
        }
    }

    public void unSubscribe() {
        getItemsOfGroupUC.unsubscribe();
    }

    public void setCurrentCategory(String currentCategory) {
        this.currentCategory = currentCategory;
    }
}
