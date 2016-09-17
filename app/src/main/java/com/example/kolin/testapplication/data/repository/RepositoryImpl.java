package com.example.kolin.testapplication.data.repository;

import com.example.kolin.testapplication.data.net.Rest;
import com.example.kolin.testapplication.data.orm.RealmQueries;
import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.FoodCategory;
import com.example.kolin.testapplication.domain.ItemOfGroup;
import com.example.kolin.testapplication.domain.groups.Group;
import com.example.kolin.testapplication.domain.repository.Repository;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by kolin on 06.09.2016.
 */

public class RepositoryImpl implements Repository {


    private Rest rest;
    private RealmQueries realmQueries;

    public RepositoryImpl() {
        rest = new Rest();
        realmQueries = new RealmQueries();
    }

    @Override
    public Observable<List<ItemOfGroup>> getRestaurants(@Group.GroupAllFood String categoryName) {
        return rest.getRestaurantsFromCloud(categoryName);
    }

    @Override
    public Observable<HashMap<FoodCategory, List<Food>>> getFood(String itemGroupName) {
        realmQueries.getFavoriteFood();
        return rest.getFoodFromCloud(itemGroupName);
    }

    @Override
    public void addFoodToFavorite(Food food) {
        realmQueries.addFoodToFavorite(food);
    }

    @Override
    public Observable<List<Food>> getFavoriteFood() {
        return realmQueries.getFavoriteFood();
    }


}
