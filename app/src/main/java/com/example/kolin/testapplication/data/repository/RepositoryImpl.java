package com.example.kolin.testapplication.data.repository;

import com.example.kolin.testapplication.data.local.CalculatorListFood;
import com.example.kolin.testapplication.data.net.Rest;
import com.example.kolin.testapplication.data.orm.RealmQueries;
import com.example.kolin.testapplication.domain.CalculatedFood;
import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.FoodCategory;
import com.example.kolin.testapplication.domain.ItemOfGroup;
import com.example.kolin.testapplication.domain.VitalCharacteristic;
import com.example.kolin.testapplication.domain.groups.Group;
import com.example.kolin.testapplication.domain.repository.Repository;

import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.subjects.ReplaySubject;

/**
 * Created by kolin on 06.09.2016.
 */

public class RepositoryImpl implements Repository {


    private Rest rest;
    private RealmQueries realmQueries;
    private CalculatorListFood calculatorListFood;


    public RepositoryImpl() {
        rest = new Rest();
        realmQueries = new RealmQueries();
        calculatorListFood = CalculatorListFood.getInstance();
    }

    @Override
    public Observable<List<ItemOfGroup>> getRestaurants(@Group.GroupAllFood String categoryName) {
        return rest.getRestaurantsFromCloud(categoryName);
    }

    @Override
    public Observable<List<ItemOfGroup>> getAllItemsOfGroup() {
        return rest.getRestaurantsFromCloud(Group.RESTAURANT)
                .concatWith(rest.getRestaurantsFromCloud(Group.FOOD));
    }

    @Override
    public Observable<HashMap<FoodCategory, List<Food>>> getFood(String itemGroupName) {
        return rest.getFoodFromCloud(itemGroupName);
    }

    @Override
    public void addFoodToFavorite(Food food) {
        realmQueries.addFavoriteFood(food);
    }

    @Override
    public Observable<List<Food>> getFavoriteFood() {
        return realmQueries
                .getFavoriteFood();
    }

    @Override
    public void deleteFavoriteFood(Food food) {
        realmQueries.deleteFavoriteFood(food);
    }

    @Override
    public void addCalculationFood(Food food) {
        calculatorListFood.addCalculationFood(food);
    }

    @Override
    public ReplaySubject<List<Food>> getCalculationFood() {
        return calculatorListFood.getCalculatedFood();
    }

    @Override
    public void deleteCalculationFood(Food food) {
        calculatorListFood.deleteFromCalculatedFood(food);
    }

    @Override
    public void onNextCalculationFood() {
        calculatorListFood.onNext();
    }

    @Override
    public void deleteAllFromCalculation() {
        calculatorListFood.clearCalculatedFood();
    }

    @Override
    public Observable<List<VitalCharacteristic>> getVitalCharacteristic() {
        return realmQueries.getVitalCharacteristic();
    }

    @Override
    public void addVitalCharacteristic(VitalCharacteristic vitalCharacteristic) {
        realmQueries.addVitalCharacteristic(vitalCharacteristic);
    }

    @Override
    public void deleteVitalCharacteristic(VitalCharacteristic vitalCharacteristic) {
        realmQueries.deleteVitalCharacteristic(vitalCharacteristic);
    }


    @Override
    public void addResultCalculatedFood(CalculatedFood calculatedFood) {
        realmQueries.addResultCalculatedFood(calculatedFood);
    }

    @Override
    public void deleteResultCalculatedFood(CalculatedFood calculatedFood) {
        realmQueries.deleteResultCalculatedFood(calculatedFood);

    }

    @Override
    public Observable<List<CalculatedFood>> getResultCalculatedFood() {
        return realmQueries.getResultCalculatedFood();
    }
}
