package com.example.kolin.testapplication.presentation.common.weightdialog;

import android.util.Log;

import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.interactor.GetObservableCalculatedFoodUC;
import com.example.kolin.testapplication.presentation.common.AbstractPresenter;

/**
 * Created by kolin on 04.10.2016.
 */

public class WeightPresenter extends AbstractPresenter<WeightView> {

    private static final String TAG = WeightPresenter.class.getSimpleName();

    private GetObservableCalculatedFoodUC getObservableCalculatedFoodUC;

    private Food currentFood;


    public WeightPresenter() {
        getObservableCalculatedFoodUC = new GetObservableCalculatedFoodUC();
    }

    public Food getCurrentFood() {
        return currentFood;
    }

    public void setCurrentFood(Food currentFood) {
        this.currentFood = currentFood;
    }

    public void showAllValues(){

        if (!isViewAttach()){
            Log.e(TAG, "View is detach");
        }

        getWeakReference().showFoodAttribute(currentFood);
    }

    public void addFoodToCalc(){
        getObservableCalculatedFoodUC.addCalculationFood(currentFood);

        if (!isViewAttach()){
            Log.e(TAG, "View is detach");
        }

        getWeakReference().showToast("Добавлено в калькулятор!");
    }

    public void onNext(){
        getObservableCalculatedFoodUC.onNext();
    }
}
