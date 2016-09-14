package com.example.kolin.testapplication.data.orm;

import android.util.Log;

import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.FoodCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by kolin on 14.09.2016.
 */

public class RealQueries {

    private Realm realm;

    public RealQueries() {
        this.realm = RealmSingleton.getInstance();
    }

    public void addFoodToFavorite(final Food food) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(food);
            }
        });
    }

    public Observable<HashMap<FoodCategory, List<Food>>> getFavoriteFood() {
        Observable<HashMap<FoodCategory, List<Food>>> hashMapObservable = realm.where(Food.class)
                .isNotNull("idName")
                .findAll()
                .asObservable()
                .filter(new Func1<RealmResults<Food>, Boolean>() {
                    @Override
                    public Boolean call(RealmResults<Food> foods) {
                        return foods.isLoaded();
                    }
                })
                .flatMap(new Func1<RealmResults<Food>, Observable<HashMap<FoodCategory, List<Food>>>>() {
                    @Override
                    public Observable<HashMap<FoodCategory, List<Food>>> call(RealmResults<Food> foods) {
                        HashMap<FoodCategory, List<Food>> map = new HashMap<>();
                        List<Food> list = new ArrayList<>();
                        for (Food food : foods) {
                            Log.e("ЕДА", food.getName());
                            Log.e("ЕДА", food.getOwner());
                            list.add(food);
                        }
                        FoodCategory foodCategory = new FoodCategory();
                        foodCategory.setDefaults();
                        map.put(foodCategory, list);
                        return Observable.just(map);
                    }
                });
        return hashMapObservable;
    }

}
