package com.example.emma_tel.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Page implements Serializable {

    @SerializedName("id")
    private
    Integer id;

    @SerializedName("content")
    private
    String content;

    @SerializedName("title")
    private
    String title;



    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }


}
