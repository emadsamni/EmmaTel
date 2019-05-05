package com.example.emma_tel.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Complaint implements Serializable {
    @SerializedName("id")
    private
    Integer id;

    @SerializedName("user_id")
    private
    String user_id;


    @SerializedName("content")
    private
    String content;
}
