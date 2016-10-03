package com.example.kolin.testapplication.domain;

import java.util.List;

/**
 * Created by kolin on 03.10.2016.
 */

public class CalculatedFood {

    private List<Food> foodList;
    private double sumYWeight;
    private double he;
    private double insulin;

    public CalculatedFood() {
    }


    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public double getSumYWeight() {
        return sumYWeight;
    }

    public void setSumYWeight(double sumYWeight) {
        this.sumYWeight = sumYWeight;
    }

    public double getHe() {
        return he;
    }

    public void setHe(double he) {
        this.he = he;
    }

    public double getInsulin() {
        return insulin;
    }

    public void setInsulin(double insulin) {
        this.insulin = insulin;
    }
}
