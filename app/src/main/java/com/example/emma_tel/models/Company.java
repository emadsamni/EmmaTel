package com.example.emma_tel.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Company implements Serializable {

    @SerializedName("id")
    private
    Integer id;

    @SerializedName("name")
    private
    String name;

    @SerializedName("image")
    private
    String image;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
