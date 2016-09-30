package com.example.kolin.testapplication.presentation.products.catalog.favoritelist;

import android.util.Log;

import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.interactor.DefaultSubscriber;
import com.example.kolin.testapplication.domain.interactor.DeleteFavoriteFoodUC;
import com.example.kolin.testapplication.domain.interactor.GetObservableCalculatedFoodUC;
import com.example.kolin.testapplication.domain.interactor.GetFavoriteFoodUC;
import com.example.kolin.testapplication.presentation.common.AbstractPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolin on 18.09.2016.
 */

public class FavoritePresenter extends AbstractPresenter<FavoriteView> {

    private static final String TAG = FavoritePresenter.class.getSimpleName();

    private GetFavoriteFoodUC getFavoriteFoodUC;
    private DeleteFavoriteFoodUC deleteFavoriteFoodUC;
    private GetObservableCalculatedFoodUC getCalculatedFoodUC;

    private List<Food> loadedData = new ArrayList<>();


    public FavoritePresenter() {
        getFavoriteFoodUC = new GetFavoriteFoodUC();
        deleteFavoriteFoodUC = new DeleteFavoriteFoodUC();
        getCalculatedFoodUC = new GetObservableCalculatedFoodUC();
    }

    public void loadFavorite(){
        getFavoriteFoodUC.execute(new FavoriteSubscriber());
    }

    public void loadCalculated(){
        getCalculatedFoodUC.execute(new FavoriteCalculatorSubscriber());
    }

    public void showLoadedData(List<Food> foodList){
        loadedData.clear();
        loadedData.addAll(foodList);
        if (!isViewAttach()){
            Log.e(TAG, "View is detach!");
        }

        getWeakReference().showFavoriteFood(foodList);
    }

    private final class FavoriteSubscriber extends DefaultSubscriber<List<Food>>{
        @Override
        public void onNext(List<Food> list) {
            FavoritePresenter.this.showLoadedData(list);
        }
    }

    private final class FavoriteCalculatorSubscriber extends DefaultSubscriber<List<Food>>{
        @Override
        public void onNext(List<Food> foodList) {
            setCalculatedFood(foodList);
        }
    }


    public void unSubscribe(){
        getFavoriteFoodUC.unsubscribe();
        getCalculatedFoodUC.unsubscribe();
    }

    public void deleteFromFavorite(Food food){
        deleteFavoriteFoodUC.execute(food);

        if (!isViewAttach()){
            Log.e(TAG, "View is detach!");
        }

        getWeakReference().showSnackBar("Удалено из избраннного!");
    }

    public void deleteCalculationFood(Food food){
        getCalculatedFoodUC.deleteCalculationFood(food);

        if (!isViewAttach()){
            Log.e(TAG, "View is detach!");
        }

        getWeakReference().showSnackBar("Удалено из калькулятора!");
    }

    public void addCalculationFood(Food food){
        getCalculatedFoodUC.addCalculationFood(food);

        if (!isViewAttach()){
            Log.e(TAG, "View is detach!");
        }

        getWeakReference().showSnackBar("Добавлено в калькулятор!");
    }

    public void setCalculatedFood(List<Food> foodList){
        if (!isViewAttach()){
            Log.e(TAG, "View is detach!");
        }

        getWeakReference().addCheckedFood(foodList);
    }
}

