package com.example.kolin.testapplication.domain.calculation;

import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.VitalCharacteristic;

import java.util.List;

/**
 * Created by kolin on 03.10.2016.
 */

public class FoodCalculation {

    private static double sumB(List<Food> list) {
        double sum = 0;
        for (Food food : list) {
            sum += food.getB();
        }
        return sum;
    }

    private static double sumJ(List<Food> list) {
        double sum = 0;
        for (Food food : list) {
            sum += food.getJ();
        }
        return sum;
    }

    private static double sumY(List<Food> list) {
        double sum = 0;
        for (Food food : list) {
            sum += food.getY();
        }
        return sum;
    }

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
        double y = sumY(foodList);
        double weight = sumWeight(foodList);
        double b = sumB(foodList);
        double j = sumJ(foodList);

        double first = y /
                100 * (100 - vitalCharacteristic.getGi()) /
                100 * weight /
                vitalCharacteristic.getHe() * vitalCharacteristic.getkOne();

        double second = y /
                100 * vitalCharacteristic.getGi() /
                100 * weight /
                vitalCharacteristic.getHe() * vitalCharacteristic.getkOne();

        double third = b /
                100 * weight * 4.1 /
                100 * vitalCharacteristic.getkTwo();

        double fourth = j /
                100 * weight * 9.3 /
                100 * vitalCharacteristic.getkTwo();

        return first + second + third + fourth;
    }


}
