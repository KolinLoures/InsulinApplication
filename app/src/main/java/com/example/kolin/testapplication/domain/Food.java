package com.example.kolin.testapplication.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kolin on 11.09.2016.
 */

public class Food {

    private Double b;
    private Integer foodCategory;
    @SerializedName("id_name")
    private String idName;
    private Integer j;
    private String name;
    private Integer weight;
    private Double y;

    /**
     * @return The b
     */
    public Double getB() {
        return b;
    }

    /**
     * @param b The b
     */
    public void setB(Double b) {
        this.b = b;
    }

    /**
     * @return The foodCategory
     */
    public Integer getFoodCategory() {
        return foodCategory;
    }

    /**
     * @param foodCategory The food_category
     */
    public void setFoodCategory(Integer foodCategory) {
        this.foodCategory = foodCategory;
    }

    /**
     * @return The idName
     */
    public String getIdName() {
        return idName;
    }

    /**
     * @param idName The id_name
     */
    public void setIdName(String idName) {
        this.idName = idName;
    }

    /**
     * @return The j
     */
    public Integer getJ() {
        return j;
    }

    /**
     * @param j The j
     */
    public void setJ(Integer j) {
        this.j = j;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * @param weight The weight
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * @return The y
     */
    public Double getY() {
        return y;
    }

    /**
     * @param y The y
     */
    public void setY(Double y) {
        this.y = y;
    }

}
