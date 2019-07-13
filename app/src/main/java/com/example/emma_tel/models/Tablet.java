package com.example.emma_tel.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Tablet implements Serializable {

    @SerializedName("id")
    private
    Integer id;

    @SerializedName("name")
    private
    String name;

    @SerializedName("networks")
    private
    String networks;

    @SerializedName("cpu")
    private
    String cpu;

    @SerializedName("main_camera")
    private
    String main_camera;

    @SerializedName("front_camera")
    private
    String front_camera;

    @SerializedName("battery")
    private
    String battery;

    @SerializedName("ram")
    private
    String ram;

    @SerializedName("internal_storage")
    private
    String internal_storage;

    @SerializedName("image")
    private
    String image;

    @SerializedName("price")
    private
    String price;

    @SerializedName("other_details")
    private
    String other_details;

    @SerializedName("colors")
    private
    String colors;


    @SerializedName("category")
    private
    Category category;

    @SerializedName("company")
    private
    Company company;

    @SerializedName("screen")
    private
    String screen;

    public String  getScreen() {
        return screen;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNetworks() {
        return networks;
    }

    public String getCpu() {
        return cpu;
    }

    public String getMain_camera() {
        return main_camera;
    }

    public String getFront_camera() {
        return front_camera;
    }

    public String getBattery() {
        return battery;
    }

    public String getRam() {
        return ram;
    }

    public String getInternal_storage() {
        return internal_storage;
    }

    public String getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public String getOther_details() {
        return other_details;
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
