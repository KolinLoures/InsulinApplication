package com.example.kolin.testapplication.domain.calculation;

import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.VitalCharacteristic;

import java.util.List;

/**
 * Created by kolin on 03.10.2016.
 */

public class FoodCalculation {

    public static double sumWeight(List<Food> list) {
        double sum = 0;
        for (Food food : list) {
            sum += food.getWeight();
        }
        return sum;
    }

    public static double getYOnWeight(Food food) {
        return food.getY() * food.getWeight() / 100;
    }

    public static double getBOnWeight(Food food) {
        return food.getB() * food.getWeight() / 100;
    }

    public static double getJOnWeight(Food food) {
        return food.getJ() * food.getWeight() / 100;
    }


    public static double getSumYOnWeight(List<Food> foodList) {
        double sum = 0;
        for (Food food : foodList) {
            sum += getYOnWeight(food);
        }
        return sum;
    }

    public static double getSumJOnWeight(List<Food> foodList) {
        double sum = 0;
        for (Food food : foodList) {
            sum += getJOnWeight(food);
        }
        return sum;
    }

    public static double getSumBOnWeight(List<Food> foodList) {
        double sum = 0;
        for (Food food : foodList) {
            sum += getBOnWeight(food);
        }
        return sum;
    }


    public static double getInsuline(List<Food> foodList, VitalCharacteristic vitalCharacteristic) {

        double first;
        double second;
        double third;
        double fourth;
        double s;

        double insulin = 0;

        for (Food f : foodList) {

            first = f.getY() /
                    100 * (100 - vitalCharacteristic.getGi()) /
                    100 * f.getWeight() /
                    vitalCharacteristic.getHe() * vitalCharacteristic.getkOne();

            second = f.getY() /
                    100 * vitalCharacteristic.getGi() /
                    100 * f.getWeight() /
                    vitalCharacteristic.getHe() * vitalCharacteristic.getkOne();

            third = f.getB() /
                    100 * f.getWeight() * 4.1 /
                    100 * vitalCharacteristic.getkTwo();

            fourth = f.getJ() /
                    100 * f.getWeight() * 9.3 /
                    100 * vitalCharacteristic.getkTwo();

            s = first + second + third + fourth;
            insulin += s;
        }

        return insulin;
    }


}
