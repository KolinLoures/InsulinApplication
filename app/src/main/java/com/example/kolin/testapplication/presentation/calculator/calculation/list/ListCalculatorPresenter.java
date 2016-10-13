package com.example.kolin.testapplication.presentation.calculator.calculation.list;

import android.util.Log;

import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.interactor.DefaultSubscriber;
import com.example.kolin.testapplication.domain.interactor.GetObservableCalculatedFoodUC;
import com.example.kolin.testapplication.presentation.common.AbstractPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolin on 03.10.2016.
 */

public class ListCalculatorPresenter extends AbstractPresenter<ListCalculatorView> {

    private static final String TAG = ListCalculatorPresenter.class.getSimpleName();
    private List<Food> list = new ArrayList<>();

    private GetObservableCalculatedFoodUC getObservableCalculatedFoodUC;

    public ListCalculatorPresenter() {
        getObservableCalculatedFoodUC = new GetObservableCalculatedFoodUC();
    }

    public void load() {
        getObservableCalculatedFoodUC.execute(new ListCalculatorSubscriber());
    }

    private final class ListCalculatorSubscriber extends DefaultSubscriber<List<Food>> {
        @Override
        public void onNext(List<Food> foodList) {
            showData(foodList);
        }

        @Override
        public void onError(Throwable e) {
            Log.e("Errot", e.toString());
        }
    }

    public void showData(List<Food> foodList) {
        if (!isViewAttach()) {
            Log.e(TAG, "View is detach!");
        }

        getWeakReference().showCalculationFood(foodList);
    }

    public void deleteFromCalculated(Food food) {
        getObservableCalculatedFoodUC.deleteCalculationFood(food);

        if (!isViewAttach()) {
            Log.e(TAG, "View is detach!");
        }

        getWeakReference().showSnackBar("Удалено из списка!");
    }

    public void unSubscribe(){
        getObservableCalculatedFoodUC.unsubscribe();
    }


}
