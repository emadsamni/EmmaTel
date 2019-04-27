package com.example.emma_tel.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("id")
    private
    Integer id;

    @SerializedName("full_name")
    private
    String full_name;

    @SerializedName("phone")
    private
    String phone;

    @SerializedName("device_name")
    private
    String device_name;

    public Integer getId() {
        return id;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getDevice_name() {
        return device_name;
    }
}
