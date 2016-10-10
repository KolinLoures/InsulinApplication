package com.example.kolin.testapplication.domain;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kolin on 03.10.2016.
 */

public class VitalCharacteristic extends RealmObject implements Serializable {

    @PrimaryKey
    private String name;
    private double he;
    private double kOne;
    private double kTwo;
    private double gi;

    public VitalCharacteristic() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHe() {
        return he;
    }

    public void setHe(double he) {
        this.he = he;
    }

    public double getkOne() {
        return kOne;
    }

    public void setkOne(double kOne) {
        this.kOne = kOne;
    }

    public double getkTwo() {
        return kTwo;
    }

    public void setkTwo(double kTwo) {
        this.kTwo = kTwo;
    }

    public double getGi() {
        return gi;
    }

    public void setGi(double gi) {
        this.gi = gi;
    }
}
