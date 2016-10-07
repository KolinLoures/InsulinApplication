package com.example.kolin.testapplication.presentation.calculator.calculation.result;

import android.util.Log;

import com.example.kolin.testapplication.domain.CalculatedFood;
import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.VitalCharacteristic;
import com.example.kolin.testapplication.domain.calculation.FoodCalculation;
import com.example.kolin.testapplication.domain.interactor.DefaultSubscriber;
import com.example.kolin.testapplication.domain.interactor.GetObservableCalculatedFoodUC;
import com.example.kolin.testapplication.presentation.common.AbstractPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolin on 07.10.2016.
 */

public class ResultPresenter extends AbstractPresenter<ResultView> {

    private static final String TAG = ResultPresenter.class.getSimpleName();

    private List<Food> lodedList = new ArrayList<>();

    private GetObservableCalculatedFoodUC getObservableCalculatedFoodUC;

    public ResultPresenter() {
        getObservableCalculatedFoodUC = new GetObservableCalculatedFoodUC();
    }

    public void load(){
        getObservableCalculatedFoodUC.execute(new ResultSubscriber());
    }

    private final class ResultSubscriber extends DefaultSubscriber<List<Food>>{
        @Override
        public void onNext(List<Food> foodList) {
            showLoadedData(foodList);
        }
    }

    public void showLoadedData(List<Food> foodList){
        lodedList.clear();
        lodedList.addAll(foodList);
        if (!isViewAttach()){
            Log.e(TAG, "View is detach!");
        }

        getWeakReference().showLoadedData(foodList);
    }

    public void computeAllValues(List<Food> list, VitalCharacteristic vitalCharacteristic) {
        CalculatedFood calculatedFood = new CalculatedFood();
        double sumYOnWeight = FoodCalculation.getSumYOnWeight(list);


        calculatedFood.setFoodList(list);
        calculatedFood.setSumYWeight(sumYOnWeight);
        calculatedFood.setHe(sumYOnWeight / vitalCharacteristic.getHe());
        calculatedFood.setInsulin(FoodCalculation.getInsuline(list, vitalCharacteristic));

        if (!isViewAttach()) {
            Log.e(TAG, "View is detach!");
        }

//        getWeakReference().showComputeResult(calculatedFood);
    }
}
