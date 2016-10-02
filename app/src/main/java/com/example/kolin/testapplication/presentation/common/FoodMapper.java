package com.example.kolin.testapplication.presentation.common;

import com.example.kolin.testapplication.domain.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolin on 02.10.2016.
 */

public class FoodMapper {

    public static List<Food> realmListToList(List<Food> foodList){
        List<Food> list = new ArrayList<Food>();
        for (Food food: foodList){
            Food f = new Food();
            f.setName(food.getName());
            f.setChecked(false);
            f.setIdName(food.getIdName());
            f.setOwner(food.getOwner());
            f.setWeight(food.getWeight());
            f.setB(food.getB());
            f.setJ(food.getJ());
            f.setY(food.getY());
            f.setIdOwner(food.getIdOwner());
            list.add(f);
        }
        return list;
    }
}
