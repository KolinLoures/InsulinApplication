package com.example.kolin.testapplication.presentation.products.catalog.foodlist;

import android.util.Log;

import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.FoodCategory;
import com.example.kolin.testapplication.domain.interactor.DefaultSubscriber;
import com.example.kolin.testapplication.domain.interactor.GetFoodUC;
import com.example.kolin.testapplication.domain.interactor.SetFoodToFavoriteUC;
import com.example.kolin.testapplication.presentation.common.AbstractPresenter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by kolin on 12.09.2016.
 */

public class ListFoodPresenter extends AbstractPresenter<ListFoodView> {

    private static final String TAG = ListFoodPresenter.class.getSimpleName();
    private GetFoodUC getFoodUC;
    private SetFoodToFavoriteUC setFoodToFavoriteUC;

    public ListFoodPresenter() {
        getFoodUC = new GetFoodUC();
        setFoodToFavoriteUC = new SetFoodToFavoriteUC();

    }

    public void load(String itemGroupName) {
        getFoodUC.setItemGroupName(itemGroupName);
        getFoodUC.execute(new ListFoodSubscriber());
    }

    public void showLoadedData(HashMap<FoodCategory, List<Food>> foodCategoryListHashMap) {
        if (!isViewAttach()) {
            Log.e(TAG, "View was detach");
            return;
        }

        for (HashMap.Entry<FoodCategory, List<Food>> pair: foodCategoryListHashMap.entrySet()){

        }

        getWeakReference().showLoadedFood(foodCategoryListHashMap);
    }

    public void addToFavorite(Food food) {
        setFoodToFavoriteUC.execute(food);

        if (!isViewAttach()) {
            Log.e(TAG, "View was detach");
            return;
        }

        getWeakReference().showSnackBar();
    }

    private final class ListFoodSubscriber extends DefaultSubscriber<HashMap<FoodCategory, List<Food>>> {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(HashMap<FoodCategory, List<Food>> foodCategoryListHashMap) {
                ListFoodPresenter.this.showLoadedData(foodCategoryListHashMap);
        }
    }

    public void unSubscribe() {
        getFoodUC.unsubscribe();
    }
}
