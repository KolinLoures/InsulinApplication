package com.example.kolin.testapplication.presentation.calculator.calculation.result;

import android.util.Log;

import com.example.kolin.testapplication.domain.CalculatedFood;
import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.VitalCharacteristic;
import com.example.kolin.testapplication.domain.calculation.FoodCalculation;
import com.example.kolin.testapplication.domain.interactor.DefaultSubscriber;
import com.example.kolin.testapplication.domain.interactor.GetObservableCalculatedFoodUC;
import com.example.kolin.testapplication.domain.interactor.GetResultCalculationUC;
import com.example.kolin.testapplication.domain.interactor.GetVitalCharacteristicUC;
import com.example.kolin.testapplication.presentation.common.AbstractPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolin on 07.10.2016.
 */

public class ResultPresenter extends AbstractPresenter<ResultView> {

    private static final String TAG = ResultPresenter.class.getSimpleName();

    private List<Food> loadedList = new ArrayList<>();
    private List<VitalCharacteristic> loadedVitalCharacteristicList = new ArrayList<>();
    private CalculatedFood currentCalculatedFood;

    private GetObservableCalculatedFoodUC getObservableCalculatedFoodUC;
    private GetVitalCharacteristicUC getVitalCharacteristicUC;
    private GetResultCalculationUC getResultCalculationUC;

    public ResultPresenter() {
        getObservableCalculatedFoodUC = new GetObservableCalculatedFoodUC();
        getVitalCharacteristicUC = new GetVitalCharacteristicUC();
        getResultCalculationUC = new GetResultCalculationUC();
        currentCalculatedFood = new CalculatedFood();
    }

    public void load() {
        getVitalCharacteristicUC.execute(new ResultVitalSubscriber());
    }

    private final class ResultSubscriber extends DefaultSubscriber<List<Food>> {
        @Override
        public void onNext(List<Food> foodList) {
            showLoadedData(foodList);
        }

        @Override
        public void onError(Throwable e) {
            Log.e("Error", e.toString());
        }
    }

    private final class ResultVitalSubscriber extends DefaultSubscriber<List<VitalCharacteristic>> {
        @Override
        public void onNext(List<VitalCharacteristic> list) {
            setSpinnerData(list);
            getObservableCalculatedFoodUC.execute(new ResultSubscriber());
        }

        @Override
        public void onError(Throwable e) {
            Log.e("Error Result Vital", e.toString());
        }
    }

    public void setSpinnerData(List<VitalCharacteristic> list) {
        loadedVitalCharacteristicList.clear();
        loadedVitalCharacteristicList.addAll(list);

        if (!isViewAttach()) {
            Log.e(TAG, "View is detach!");
        }

        getWeakReference().setVitalCharacteristic(list);
    }

    public void showLoadedData(List<Food> foodList) {
        loadedList.clear();
        loadedList.addAll(foodList);
        if (!isViewAttach()) {
            Log.e(TAG, "View is detach!");
        }

        getWeakReference().showLoadedData(foodList);
    }

    public void computeAllValues(int position) {

        if (position == -1) {
            position = 0;
        }

        VitalCharacteristic vitalCharacteristic = loadedVitalCharacteristicList.get(position);

        double sumYOnWeight = FoodCalculation.getSumYOnWeight(loadedList);
        double sumBOnWeight = FoodCalculation.getSumBOnWeight(loadedList);
        double sumJOnWeight = FoodCalculation.getSumJOnWeight(loadedList);

        currentCalculatedFood.setId((long) 1);
        currentCalculatedFood.setFoodList(loadedList);
        currentCalculatedFood.setSumWeight(FoodCalculation.sumWeight(loadedList));
        currentCalculatedFood.setSumYWeight(sumYOnWeight);
        currentCalculatedFood.setSumBWeight(sumBOnWeight);
        currentCalculatedFood.setSumJWeight(sumJOnWeight);
        currentCalculatedFood.setSumHe(sumYOnWeight / vitalCharacteristic.getHe());

        currentCalculatedFood.setSumInsulin(FoodCalculation.getInsuline(loadedList, vitalCharacteristic));
        currentCalculatedFood.setVitalCharacteristic(vitalCharacteristic);

        if (!isViewAttach()) {
            Log.e(TAG, "View is detach!");
        }

        getWeakReference().showComputeResult(currentCalculatedFood);
    }

    public void unSubscribe() {
        getVitalCharacteristicUC.unsubscribe();
        getObservableCalculatedFoodUC.unsubscribe();
    }

    public void saveCalculation() {
        getResultCalculationUC.addResultCalculatedFood(currentCalculatedFood);

        if (!isViewAttach()) {
            Log.e(TAG, "View is detach!");
        }

        getWeakReference().showSnackBar("Добавлено в историю!");
    }
}
