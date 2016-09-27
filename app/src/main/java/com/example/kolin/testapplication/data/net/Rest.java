package com.example.kolin.testapplication.data.net;

import com.example.kolin.testapplication.data.entities.FoodCategoryEntity;
import com.example.kolin.testapplication.data.entities.ItemOfGroupEntity;
import com.example.kolin.testapplication.data.mapper.DataMapper;
import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.FoodCategory;
import com.example.kolin.testapplication.domain.ItemOfGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by kolin on 14.09.2016.
 */

public class Rest {

    private Api api;

    public Rest() {
        this.api = RetrofitSingleton.getApi();
    }

    public Observable<List<ItemOfGroup>> getRestaurantsFromCloud(String categoryName) {
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

    public Observable<HashMap<FoodCategory, List<Food>>> getFoodFromCloud(String itemGroupName) {
        return api.getFoodFromItem(itemGroupName)
                .map(new Func1<List<FoodCategoryEntity>, HashMap<FoodCategory, List<Food>>>() {
                    @Override
                    public HashMap<FoodCategory, List<Food>> call(List<FoodCategoryEntity> foodCategoryEntities) {
                        HashMap<FoodCategory, List<Food>> map = new HashMap<>();
                        for (FoodCategoryEntity f : foodCategoryEntities) {
                            map.put(DataMapper.convertToFoodCategory(f),
                                    DataMapper.convertListToFoodList(f.getListFood()));
                        }
                        return map;
                    }
                });
    }


}
