package com.example.emma_tel.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class City implements Serializable {

    @SerializedName("id")
    private
    Integer id;

    @SerializedName("phone")
    private
    String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
