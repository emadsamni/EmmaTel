package com.example.emma_tel.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Notification  implements Serializable {

    @SerializedName("id")
    private
    Integer id;

    @SerializedName("contents")
    private
    String content;

    @SerializedName("created_at")
    private
    String created_at;

    public String getCreated_at() {
        return created_at;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }


}
