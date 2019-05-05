package com.example.emma_tel.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Branch implements Serializable {

    @SerializedName("id")
    private
    Integer id;

    @SerializedName("phone")
    private
    String phone;

    @SerializedName("fixed_phone")
    private
    String fixed_phone;

    @SerializedName("whatsapp")
    private
    String whatsapp;

    @SerializedName("title")
    private
    String title;

    @SerializedName("address")
    private
    String address;

    @SerializedName("city")
    private
    City city;

    @SerializedName("lat")
    private
    String lat;

    @SerializedName("lng")
    private
    String lng;

    public Integer getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getFixed_phone() {
        return fixed_phone;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public City getCity() {
        return city;
    }

    public String getLng() {
        return lng;
    }

    public String getLat() {
        return lat;
    }
}
