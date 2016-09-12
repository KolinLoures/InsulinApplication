package com.example.kolin.testapplication.data.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kolin on 12.09.2016.
 */

public class ItemOfGroupEntity {

    private int category;
    private String description;
    @SerializedName("id_name")
    private String idName;
    private String name;
    private String src;

    /**
     *
     * @return
     * The category
     */
    public Integer getCategory() {
        return category;
    }

    /**
     *
     * @param category
     * The category
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The idName
     */
    public String getIdName() {
        return idName;
    }

    /**
     *
     * @param idName
     * The id_name
     */
    public void setIdName(String idName) {
        this.idName = idName;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The src
     */
    public String getSrc() {
        return src;
    }

    /**
     *
     * @param src
     * The src
     */
    public void setSrc(String src) {
        this.src = src;
    }
}
