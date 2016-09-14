package com.example.kolin.testapplication.domain;

import io.realm.RealmObject;
import io.realm.annotations.Index;

/**
 * Created by kolin on 11.09.2016.
 */

public class Food extends RealmObject {

    private Double b;
    @Index
    private String idName;
    private Double j;
    private String name;
    private Integer weight;
    private Double y;
    private String owner;

    public Double getB() {
        return b;
    }

    public void setB(Double b) {
        this.b = b;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public Double getJ() {
        return j;
    }

    public void setJ(Double j) {
        this.j = j;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
