package com.example.kolin.testapplication.presentation.products.catalog.foodlist;

import android.util.Log;

import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.FoodCategory;
import com.example.kolin.testapplication.domain.interactor.DefaultSubscriber;
import com.example.kolin.testapplication.domain.interactor.GetFavoriteFoodUC;
import com.example.kolin.testapplication.domain.interactor.GetFoodUC;
import com.example.kolin.testapplication.domain.interactor.GetObservableCalculatedFoodUC;
import com.example.kolin.testapplication.presentation.common.AbstractPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kolin on 12.09.2016.
 */

public class ListFoodPresenter extends AbstractPresenter<ListFoodView> {

    private static final String TAG = ListFoodPresenter.class.getSimpleName();

    private GetFoodUC getFoodUC;
    private GetFavoriteFoodUC getFavoriteFoodUC;
    private GetObservableCalculatedFoodUC getObservableCalculatedFoodUC;

    private String currentCallItemName;
    private HashMap<FoodCategory, List<Food>> loadedData = new HashMap<>();
    private List<Food> checkedFood = new ArrayList<>();

    public ListFoodPresenter() {
        getFoodUC = new GetFoodUC();
        getFavoriteFoodUC = new GetFavoriteFoodUC();
        getObservableCalculatedFoodUC = new GetObservableCalculatedFoodUC();
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

    public void loadCalculatedFood(){
        getObservableCalculatedFoodUC.execute(new ListFoodCalcSubscriber());
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
        getFavoriteFoodUC.addFavoriteFood(food);

        if (!isViewAttach()) {
            Log.e(TAG, "View was detach");
            return;
        }

        getWeakReference().showSnackBar("Добавленно в избранное!");
    }

    public void addCalculationFood(Food food) {
        getObservableCalculatedFoodUC.addCalculationFood(food);

        if (!isViewAttach()) {
            Log.e(TAG, "View was detach");
            return;
        }

        getWeakReference().showSnackBar("Добавленно в расчет!");
    }

    private final class ListFoodSubscriber extends DefaultSubscriber<HashMap<FoodCategory, List<Food>>> {
        @Override
        public void onNext(HashMap<FoodCategory, java.util.List<Food>> foodCategoryListHashMap) {
            showLoadedData(foodCategoryListHashMap);
            loadCalculatedFood();
        }
    }

    private final class ListFoodCalcSubscriber extends DefaultSubscriber<List<Food>> {
        @Override
        public void onNext(List<Food> foodList) {
            setCalculatedFood(foodList);
        }
    }

    public void unSubscribe() {
        getFoodUC.unsubscribe();
        getObservableCalculatedFoodUC.unsubscribe();
    }

    public HashMap<FoodCategory, List<Food>> getLoadedData() {
        return loadedData;
    }

    public void deleteFromCalculatedFood(Food food){
        getObservableCalculatedFoodUC.deleteCalculationFood(food);

        if (!isViewAttach()) {
            Log.e(TAG, "View was detach");
            return;
        }

        getWeakReference().showSnackBar("Убрано из расчета!");
    }

    public void setCalculatedFood(List<Food> foodList){
        checkedFood.clear();
        checkedFood.addAll(foodList);

        if (!isViewAttach()){
            Log.e(TAG, "View is detach!");
        }

        getWeakReference().showCheckedFood(checkedFood);
    }
}
