package com.example.kolin.testapplication.data.local;

import com.example.kolin.testapplication.domain.Food;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by kolin on 26.09.2016.
 */

public class LocalCalculationData {

    private static List<Food> foodList = new ArrayList<>();

    private LocalCalculationData() {}

    public static Observable<List<Food>> getObservableCalcFood(){
        return Observable.just(foodList);
    }

    public static void putFoodToObservable(Food food){
        if (foodList != null){
            foodList.add(food);
        }
    }

    public static void cleaCalcFoodObserv(){
        foodList.clear();
    }

    public static void deleteFood(Food food){
        if (foodList.contains(food)){
            foodList.remove(food);
        }
    }

}
