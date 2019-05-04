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

    public String getVerification_code() {
        return verification_code;
    }

    @SerializedName("verification_code")
    private
    String verification_code;

    @SerializedName("device_name")
    private
    String device_name;

    public String getToken() {
        return token;
    }

    @SerializedName("token")
    private
    String token;


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
