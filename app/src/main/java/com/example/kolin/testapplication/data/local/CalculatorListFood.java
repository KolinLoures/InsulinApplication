package com.example.kolin.testapplication.data.local;

import com.example.kolin.testapplication.domain.Food;

import java.util.ArrayList;
import java.util.List;

import rx.subjects.ReplaySubject;

/**
 * Created by kolin on 30.09.2016.
 */

public class CalculatorListFood {

    private static CalculatorListFood calculatorListFood = null;
    private List<Food> listFood;
    private ReplaySubject<List<Food>> replaySubject;


    private CalculatorListFood() {
        listFood = new ArrayList<>();
        replaySubject = ReplaySubject.create();
    }

    public void addCalculationFood(Food food) {
        if (!listFood.contains(food)) {
            listFood.add(food);
        }
        replaySubject.onNext(listFood);
    }

    public ReplaySubject<List<Food>> getCalculatedFood() {
        return replaySubject;
    }

    public void deleteFromCalculatedFood(Food food) {
        listFood.remove(food);

        replaySubject.onNext(listFood);
    }

    public void clearCalculatedFood() {
        listFood.clear();

        replaySubject.onNext(listFood);
    }

    public static CalculatorListFood getInstance() {
        if (calculatorListFood == null) {
            calculatorListFood = new CalculatorListFood();
        }
        return calculatorListFood;
    }


}
