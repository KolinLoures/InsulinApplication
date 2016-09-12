package com.example.kolin.testapplication.data.mapper;

import com.example.kolin.testapplication.data.entities.FoodCategoryEntity;
import com.example.kolin.testapplication.data.entities.FoodEntity;
import com.example.kolin.testapplication.data.entities.ItemOfGroupEntity;
import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.FoodCategory;
import com.example.kolin.testapplication.domain.ItemOfGroup;

/**
 * Created by kolin on 12.09.2016.
 */

public class DataMapper {

    public static ItemOfGroup convertToItemOfGroup(ItemOfGroupEntity itemOfGroupEntity){
        ItemOfGroup itemOfGroup = new ItemOfGroup();
        itemOfGroup.setName(itemOfGroupEntity.getName());
        itemOfGroup.setDescription(itemOfGroupEntity.getDescription());
        itemOfGroup.setCategory(itemOfGroupEntity.getCategory());
        itemOfGroup.setIdName(itemOfGroupEntity.getIdName());
        itemOfGroup.setSrc(itemOfGroupEntity.getSrc());
        return itemOfGroup;
    }

    public static FoodCategory convertToFoodCategory(FoodCategoryEntity foodCategoryEntity){
        FoodCategory foodCategory = new FoodCategory();
        foodCategory.setSrc(foodCategoryEntity.getSrc());
        foodCategory.setNameFoodCategory(foodCategoryEntity.getNameFoodCategory());
        return foodCategory;
    }

    public static Food convertToFood(FoodEntity foodEntity){
        Food food = new Food();
        food.setIdName(foodEntity.getIdName());
        food.setName(foodEntity.getName());
        food.setB(foodEntity.getB());
        food.setJ(foodEntity.getJ());
        food.setY(foodEntity.getY());
        food.setWeight(foodEntity.getWeight());
        return food;
    }
}
