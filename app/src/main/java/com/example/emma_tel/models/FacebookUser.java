package com.example.emma_tel.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FacebookUser  implements Serializable {

    @SerializedName("id")
    private
    Integer id;

    @SerializedName("facebook_id")
    private
    String facebook_id;


    @SerializedName("email")
    private
    String email;

    @SerializedName("name")
    private
    String name;

    public Integer getId() {
        return id;
    }

    public String getFacebook_id() {
        return facebook_id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
