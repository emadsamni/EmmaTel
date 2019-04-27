package com.example.emma_tel.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Accessory implements Serializable {

    @SerializedName("id")
    private
    Integer id;

    @SerializedName("name")
    private
    String name;

    @SerializedName("image")
    private
    String image;

    @SerializedName("price")
    private
    String price;

    @SerializedName("details")
    private
    String  details;

    @SerializedName("colors")
    private
    String colors;


    @SerializedName("category")
    private
    Category category;

    @SerializedName("company")
    private
    Company company;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public String getDetails() {
        return details;
    }

    public String getColors() {
        return colors;
    }

    public Category getCategory() {
        return category;
    }

    public Company getCompany() {
        return company;
    }
}
