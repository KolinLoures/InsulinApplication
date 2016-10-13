package com.example.kolin.testapplication.domain.groups;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolin on 04.09.2016.
 */
public class Group {

    @Retention(RetentionPolicy.RUNTIME)
    @StringDef({
            FOOD,
            RESTAURANT,
            ALL
    })

    public @interface GroupAllFood {}

    public static final String FOOD = "food";
    public static final String RESTAURANT = "restaurants";
    public static final String ALL = "all";

    private static List<String> list = new ArrayList<>();

    static {
        list.add(FOOD);
        list.add(RESTAURANT);
        list.add(ALL);
    }


    public static String getCategoryById(int idCategory){
        if (idCategory >= list.size()){
            return null;
        }
        return list.get(idCategory);
    }

    public static List<String> getList() {
        return list;
    }
}
