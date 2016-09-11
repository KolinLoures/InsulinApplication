package com.example.kolin.testapplication.domain.categories;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolin on 04.09.2016.
 */
public class Categories {

    @Retention(RetentionPolicy.RUNTIME)
    @StringDef({
            FOOD,
            RESTAURANT
    })

    public @interface Category {}

    public static final String FOOD = "food";
    public static final String RESTAURANT = "restaurants";

    private static List<String> list = new ArrayList<>();

    static {
        list.add(FOOD);
        list.add(RESTAURANT);
    }


    public static String getCategoryById(int idCategory){
        if (idCategory >= list.size()){
            return null;
        }
        return list.get(idCategory);
    }
}
