package com.example.kolin.testapplication.data.categories;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by kolin on 04.09.2016.
 */
public class Categories {

    @Retention(RetentionPolicy.RUNTIME)
    @StringDef({
            FOOD,
            RESTAURANT
    })

    public @interface CategoryName{}

    public static final String FOOD = "Еда";
    public static final String RESTAURANT = "Рестораны";

}
