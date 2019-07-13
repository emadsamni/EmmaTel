package com.example.emma_tel.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Event implements Serializable {

    @SerializedName("id")
    private
    Integer id;

    @SerializedName("content")
    private
    String content;

    @SerializedName("image")
    private
    String image;


    public Integer getId() {
        return id;
    }


    public String getContent() {
        return content;
    }

    public String getImage() {
        return image;
    }
}
