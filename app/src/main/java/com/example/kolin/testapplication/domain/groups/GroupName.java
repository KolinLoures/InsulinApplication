package com.example.kolin.testapplication.domain.groups;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolin on 11.09.2016.
 */

public class GroupName {
    @Retention(RetentionPolicy.RUNTIME)
    @StringDef({
            FOOD_NAME,
            RESTAURANT_NAME
    })

    public @interface GroupFoodName {}

    public static final String FOOD_NAME = "Еда";
    public static final String RESTAURANT_NAME = "Рестораны";

    private static List<String> list = new ArrayList<>();

    static {
        list.add(FOOD_NAME);
        list.add(RESTAURANT_NAME);
    }


    public static String getCategoryById(int idCategory){
        if (idCategory >= list.size()){
            return null;
        }
        return list.get(idCategory);
    }
}
