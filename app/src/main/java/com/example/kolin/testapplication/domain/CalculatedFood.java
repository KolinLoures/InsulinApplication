package com.example.kolin.testapplication.domain;

import java.io.Serializable;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kolin on 03.10.2016.
 */

public class CalculatedFood extends RealmObject implements Serializable {

    @PrimaryKey
    private Long id;
    private RealmList<Food> foodList;
    private double sumYWeight;
    private double sumJWeight;
    private double sumBWeight;
    private double sumWeight;
    private double sumHe;
    private double sumInsulin;
    private VitalCharacteristic vitalCharacteristic;

    public CalculatedFood() {
        foodList = new RealmList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSumWeight() {
        return sumWeight;
    }

    public void setSumWeight(double sumWeight) {
        this.sumWeight = sumWeight;
    }

    public double getSumJWeight() {
        return sumJWeight;
    }

    public void setSumJWeight(double sumJWeight) {
        this.sumJWeight = sumJWeight;
    }

    public double getSumBWeight() {
        return sumBWeight;
    }

    public void setSumBWeight(double sumBWeight) {
        this.sumBWeight = sumBWeight;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList.clear();
        this.foodList.addAll(foodList);
    }

    public double getSumYWeight() {
        return sumYWeight;
    }

    public void setSumYWeight(double sumYWeight) {
        this.sumYWeight = sumYWeight;
    }

    public double getSumHe() {
        return sumHe;
    }

    public void setSumHe(double sumHe) {
        this.sumHe = sumHe;
    }

    public double getSumInsulin() {
        return sumInsulin;
    }

    public void setSumInsulin(double sumInsulin) {
        this.sumInsulin = sumInsulin;
    }

    public VitalCharacteristic getVitalCharacteristic() {
        return vitalCharacteristic;
    }

    public void setVitalCharacteristic(VitalCharacteristic vitalCharacteristic) {
        this.vitalCharacteristic = vitalCharacteristic;
    }
}
