package com.example.kolin.testapplication.presentation.calculator;

import android.util.Log;

import com.example.kolin.testapplication.domain.CalculatedFood;
import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.interactor.DefaultSubscriber;
import com.example.kolin.testapplication.domain.interactor.GetObservableCalculatedFoodUC;
import com.example.kolin.testapplication.domain.interactor.GetResultCalculationUC;
import com.example.kolin.testapplication.presentation.common.AbstractPresenter;
import com.example.kolin.testapplication.presentation.common.FoodMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolin on 10.10.2016.
 */

public class CalculatorPresenter extends AbstractPresenter<CalculatorView> {

    private static final String TAG = CalculatorPresenter.class.getSimpleName();

    private GetResultCalculationUC getResultCalculationUC;
    private GetObservableCalculatedFoodUC getObservableCalculatedFoodUC;
    private List<CalculatedFood> loadedData = new ArrayList<>();

    public CalculatorPresenter() {
        getResultCalculationUC = new GetResultCalculationUC();
        getObservableCalculatedFoodUC = new GetObservableCalculatedFoodUC();
    }

    public void load(){
        getResultCalculationUC.execute(new CalculatorSubscriber());
    }

    private final class CalculatorSubscriber extends DefaultSubscriber<List<CalculatedFood>>{
        @Override
        public void onNext(List<CalculatedFood> list) {
            showLoadedData(list);
        }
    }

    public void showLoadedData(List<CalculatedFood> list){
        loadedData.clear();
        loadedData.addAll(list);

        if (!isViewAttach()){
            Log.e(TAG, "View is detach!");
        }

        getWeakReference().showLoadedData(list);
    }

    public void deleteResultCalculation(int position){
        getResultCalculationUC.deleteResultCalculatedFood(loadedData.get(position));
        if (!isViewAttach()){
            Log.e(TAG, "View is detach!");
        }

        getWeakReference().showSnackBar("Удалено из истории!");
    }

    public void openCalculator(int position){
        CalculatedFood calculatedFood = loadedData.get(position);
        List<Food> foods = FoodMapper.realmListToList(calculatedFood.getFoodList());
        for (Food food: foods){
            getObservableCalculatedFoodUC.addCalculationFood(food);
        }

        getWeakReference().openCalculator(calculatedFood.getId());
    }

    public void unSubscribe(){
        getResultCalculationUC.unsubscribe();
    }

}
