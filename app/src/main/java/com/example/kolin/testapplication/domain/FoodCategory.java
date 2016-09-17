package com.example.kolin.testapplication.domain;

/**
 * Created by kolin on 12.09.2016.
 */

public class FoodCategory {

    private String src;
    private String nameFoodCategory;


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

    public void setDefaults(){
        this.src = "file://res/drawable/ic_star_black_24dp.xml";
        this.nameFoodCategory = "ЛЮБИМЫЕ";
    }


    @Override
    public String toString() {
        return "FoodCategory{" +
                "src='" + src + '\'' +
                ", nameFoodCategory='" + nameFoodCategory + '\'' +
                '}';
    }
}
