package com.example.kolin.testapplication.domain;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kolin on 11.09.2016.
 */

public class Food extends RealmObject {

    @PrimaryKey
    private String idName;
    private Double b;
    private Double j;
    private String name;
    private Integer weight;
    private Double y;
    private String owner;

    @Ignore
    private Boolean isChecked;

    public Food() {
        isChecked = false;
    }

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

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        if (b != null ? !b.equals(food.b) : food.b != null) return false;
        if (idName != null ? !idName.equals(food.idName) : food.idName != null) return false;
        if (j != null ? !j.equals(food.j) : food.j != null) return false;
        if (name != null ? !name.equals(food.name) : food.name != null) return false;
        if (weight != null ? !weight.equals(food.weight) : food.weight != null) return false;
        if (y != null ? !y.equals(food.y) : food.y != null) return false;
        return owner != null ? owner.equals(food.owner) : food.owner == null;

    }

    @Override
    public int hashCode() {
        int result = b != null ? b.hashCode() : 0;
        result = 31 * result + (idName != null ? idName.hashCode() : 0);
        result = 31 * result + (j != null ? j.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (y != null ? y.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }


}
