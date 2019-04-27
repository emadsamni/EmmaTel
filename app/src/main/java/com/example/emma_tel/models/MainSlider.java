package com.example.emma_tel.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MainSlider implements Serializable {

    @SerializedName("id")
    private
    Integer id;

    @SerializedName("contents")
    private
    String content;

    @SerializedName("title")
    private
    String title;

    @SerializedName("image")
    private
    String image;

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }
}
