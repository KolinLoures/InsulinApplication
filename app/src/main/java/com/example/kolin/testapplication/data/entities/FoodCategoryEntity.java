package com.example.kolin.testapplication.data.entities;

import com.example.kolin.testapplication.domain.Food;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolin on 12.09.2016.
 */

public class FoodCategoryEntity {

    @SerializedName("list")
    private List<Food> listFood = new ArrayList<>();
    private String src;
    @SerializedName("name_food_category")
    private String nameFoodCategory;

    public List<Food> getListFood() {
        return listFood;
    }

    public void setListFood(List<Food> listFood) {
        this.listFood = listFood;
    }

    public String getNameFoodCategory() {
        return nameFoodCategory;
    }

    public void setNameFoodCategory(String nameFoodCategory) {
        this.nameFoodCategory = nameFoodCategory;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
