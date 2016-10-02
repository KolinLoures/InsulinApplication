package com.example.kolin.testapplication.data.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kolin on 12.09.2016.
 */

public class FoodEntity {

    private Double b;
    @SerializedName("id_name")
    private String idName;
    private Double j;
    private String name;
    private Integer weight;
    private Double y;
    private String owner;
    @SerializedName("id_owner")
    private String idOwner;

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

    public String getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(String idOwner) {
        this.idOwner = idOwner;
    }
}
