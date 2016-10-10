package com.example.kolin.testapplication.domain.groups;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import static com.example.kolin.testapplication.domain.groups.Group.FOOD;

/**
 * Created by kolin on 10.10.2016.
 */

public class DayGroup {

    @Retention(RetentionPolicy.RUNTIME)
    @StringDef({
            MORNING,
            DAY,
            EVENING,
            NIGHT,
            OTHER
    })

    public @interface PathDayGroup {}

    public static final String MORNING = "Утро";
    public static final String DAY = "День";
    public static final String EVENING = "Вечер";
    public static final String NIGHT = "Ночь";
    public static final String OTHER = "Другое";

    private static List<String> list = new ArrayList<>();

    static {
        list.add(FOOD);
        list.add(DAY);
        list.add(EVENING);
        list.add(NIGHT);
        list.add(OTHER);
    }


    public static String getDayGroupById(int idDay){
        if (idDay >= list.size()){
            return null;
        }
        return list.get(idDay);
    }

    public static int getDayId(String day){
        if (!list.contains(day)){
            return -1;
        }
        return list.indexOf(day);
    }

    public static List<String> getList(){
        return list;
    }
}
