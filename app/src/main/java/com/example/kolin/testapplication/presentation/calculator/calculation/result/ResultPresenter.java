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

    private List<Food> loadedList = new ArrayList<>();

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
        loadedList.clear();
        loadedList.addAll(foodList);
        if (!isViewAttach()){
            Log.e(TAG, "View is detach!");
        }

        getWeakReference().showLoadedData(foodList);
    }

    public void computeAllValues(VitalCharacteristic vitalCharacteristic) {
        CalculatedFood calculatedFood = new CalculatedFood();
        double sumYOnWeight = FoodCalculation.getSumYOnWeight(loadedList);
        double sumBOnWeight = FoodCalculation.getSumBOnWeight(loadedList);
        double sumJOnWeight = FoodCalculation.getSumJOnWeight(loadedList);

        calculatedFood.setFoodList(loadedList);
        calculatedFood.setSumWeight(FoodCalculation.sumWeight(loadedList));
        calculatedFood.setSumYWeight(sumYOnWeight);
        calculatedFood.setSumBWeight(sumBOnWeight);
        calculatedFood.setSumJWeight(sumJOnWeight);
        calculatedFood.setSumHe(sumYOnWeight / vitalCharacteristic.getHe());
        calculatedFood.setSumInsulin(FoodCalculation.getInsuline(loadedList, vitalCharacteristic));
        calculatedFood.setValueGi(vitalCharacteristic.getGi());
        calculatedFood.setValueHe(vitalCharacteristic.getHe());
        calculatedFood.setValueKone(vitalCharacteristic.getkOne());
        calculatedFood.setValueKtwo(vitalCharacteristic.getkTwo());

        if (!isViewAttach()) {
            Log.e(TAG, "View is detach!");
        }

        getWeakReference().showComputeResult(calculatedFood);
    }
}
