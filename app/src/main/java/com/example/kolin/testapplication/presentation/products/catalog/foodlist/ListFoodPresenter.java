package com.example.kolin.testapplication.presentation.products.catalog.foodlist;

import android.util.Log;

import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.FoodCategory;
import com.example.kolin.testapplication.domain.interactor.AddFavoriteFoodUC;
import com.example.kolin.testapplication.domain.interactor.DefaultSubscriber;
import com.example.kolin.testapplication.domain.interactor.GetFoodUC;
import com.example.kolin.testapplication.presentation.common.AbstractPresenter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by kolin on 12.09.2016.
 */

public class ListFoodPresenter extends AbstractPresenter<ListFoodView> {

    private static final String TAG = ListFoodPresenter.class.getSimpleName();

    private GetFoodUC getFoodUC;
    private AddFavoriteFoodUC addFavoriteFoodUC;

    private String currentCallItemName;
    private HashMap<FoodCategory, List<Food>> loadedData = new HashMap<>();
    private Set<Food> checkedFood = new HashSet<>();

    public ListFoodPresenter() {
        getFoodUC = new GetFoodUC();
        addFavoriteFoodUC = new AddFavoriteFoodUC();
    }

    public void load(String itemGroupName) {
        if (!itemGroupName.equals(currentCallItemName)) {
            currentCallItemName = itemGroupName;
            getFoodUC.setItemGroupName(itemGroupName);
            getFoodUC.execute(new ListFoodSubscriber());
        } else {
            if (!isViewAttach()) {
                Log.e(TAG, "View was detach");
                return;
            }

            getWeakReference().showLoadedFood(loadedData);
        }
    }

    public void showLoadedData(HashMap<FoodCategory, List<Food>> foodCategoryListHashMap) {
        if (!isViewAttach()) {
            Log.e(TAG, "View was detach");
            return;
        }
        loadedData.clear();
        loadedData.putAll(foodCategoryListHashMap);
        getWeakReference().showLoadedFood(foodCategoryListHashMap);
    }

    public void addToFavorite(Food food) {
        addFavoriteFoodUC.execute(food);

        if (!isViewAttach()) {
            Log.e(TAG, "View was detach");
            return;
        }

        getWeakReference().showSnackBar("Добавленно в избранное!");
    }

    public void addCalculationFood(Food food) {

        if (!isViewAttach()) {
            Log.e(TAG, "View was detach");
            return;
        }

        getWeakReference().showSnackBar("Добавленно в расчет!");
    }

    private final class ListFoodSubscriber extends DefaultSubscriber<HashMap<FoodCategory, List<Food>>> {
        @Override
        public void onNext(HashMap<FoodCategory, List<Food>> foodCategoryListHashMap) {
            ListFoodPresenter.this.showLoadedData(foodCategoryListHashMap);
        }
    }

    public void unSubscribe() {
        getFoodUC.unsubscribe();
    }

    public HashMap<FoodCategory, List<Food>> getLoadedData() {
        return loadedData;
    }

    public Set<Food> getCheckedFood() {
        return checkedFood;
    }

    public void addCheckedFood(Food food){
        checkedFood.add(food);
    }

    public void removeFromCheckedFood(Food food){

        if (!isViewAttach()) {
            Log.e(TAG, "View was detach");
            return;
        }

        getWeakReference().showSnackBar("Убрано из расчета!");
    }
}
