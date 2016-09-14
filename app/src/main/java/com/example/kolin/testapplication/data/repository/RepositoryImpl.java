package com.example.kolin.testapplication.data.repository;

import com.example.kolin.testapplication.data.net.Rest;
import com.example.kolin.testapplication.data.orm.RealQueries;
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
    private RealQueries realQueries;

    public RepositoryImpl() {
        rest = new Rest();
        realQueries = new RealQueries();
    }

    @Override
    public Observable<List<ItemOfGroup>> getRestaurants(@Group.GroupAllFood String categoryName) {
        return rest.getRestaurantsFromCloud(categoryName);
    }

    @Override
    public Observable<HashMap<FoodCategory, List<Food>>> getFood(String itemGroupName) {
        return rest.getFoodFromCloud(itemGroupName)
                .concatWith(realQueries.getFavoriteFood());
    }


}
