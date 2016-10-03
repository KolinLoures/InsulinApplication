package com.example.kolin.testapplication.presentation.products.catalog.dialog;

import android.util.Log;

import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.interactor.DefaultSubscriber;
import com.example.kolin.testapplication.domain.interactor.GetObservableCalculatedFoodUC;
import com.example.kolin.testapplication.presentation.common.AbstractPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolin on 22.09.2016.
 */

public class DialogPresenter extends AbstractPresenter<DialogView> {

    private static final String TAG = DialogPresenter.class.getSimpleName();

    private GetObservableCalculatedFoodUC getCalculatedFoodUC;

    private List<Food> loadedData = new ArrayList<>();


    public DialogPresenter() {
        getCalculatedFoodUC = new GetObservableCalculatedFoodUC();
    }

    public void load() {
        getCalculatedFoodUC.execute(new DialogSubscriber());
    }

    private final class DialogSubscriber extends DefaultSubscriber<List<Food>>{
        @Override
        public void onNext(List<Food> foodList) {
            showLoadedData(foodList);
        }
    }

    private void showLoadedData(List<Food> foodList) {
        loadedData.clear();
        loadedData.addAll(foodList);
        if (!isViewAttach()) {
            Log.e(TAG, "View is detach!");
        }

        getWeakReference().showLoadedData(foodList);
    }



    public void removeFoodFromCalc(Food food){
        getCalculatedFoodUC.deleteCalculationFood(food);

        if (!isViewAttach()) {
            Log.e(TAG, "View is detach!");
        }

        getWeakReference().showToast("Удалено из расчета!");
    }

    public List<Food> getLoadedData() {
        return loadedData;
    }
}
