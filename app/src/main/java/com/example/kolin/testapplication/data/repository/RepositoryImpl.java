package com.example.kolin.testapplication.data.repository;

import com.example.kolin.testapplication.data.entities.FoodCategoryEntity;
import com.example.kolin.testapplication.data.entities.ItemOfGroupEntity;
import com.example.kolin.testapplication.data.mapper.DataMapper;
import com.example.kolin.testapplication.data.net.Api;
import com.example.kolin.testapplication.data.net.RetrofitSingleton;
import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.FoodCategory;
import com.example.kolin.testapplication.domain.ItemOfGroup;
import com.example.kolin.testapplication.domain.groups.Group;
import com.example.kolin.testapplication.domain.repository.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by kolin on 06.09.2016.
 */

public class RepositoryImpl implements Repository {

    private Api api;

    public RepositoryImpl() {
        api = RetrofitSingleton.getApi();
    }

    @Override
    public Observable<List<ItemOfGroup>> getRestaurants(@Group.GroupAllFood String categoryName) {
        return api.getItemsOfGroup(categoryName)
                .map(new Func1<List<ItemOfGroupEntity>, List<ItemOfGroup>>() {
                    @Override
                    public List<ItemOfGroup> call(List<ItemOfGroupEntity> itemOfGroupEntities) {
                        List<ItemOfGroup> list = new ArrayList<>();
                        for (ItemOfGroupEntity i : itemOfGroupEntities) {
                            list.add(DataMapper.convertToItemOfGroup(i));
                        }
                        return list;
                    }
                });
    }

    @Override
    public Observable<HashMap<FoodCategory, List<Food>>> getFood(String itemGroupName) {
        return api.getFoodFromItem(itemGroupName)
                .map(new Func1<List<FoodCategoryEntity>, HashMap<FoodCategory, List<Food>>>() {
                    @Override
                    public HashMap<FoodCategory, List<Food>> call(List<FoodCategoryEntity> foodCategoryEntities) {
                        HashMap<FoodCategory, List<Food>> map = new HashMap<>();
                        for (FoodCategoryEntity f : foodCategoryEntities) {
                            map.put(DataMapper.convertToFoodCategory(f), f.getListFood());
                        }
                        return map;
                    }
                });
    }


}
