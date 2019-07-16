package com.example.emma_tel.video_time_line;

import com.example.emma_tel.models.EMedia;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VideoModel  implements Serializable {

    public VideoModel(EMedia eMedia) {
        this.id = eMedia.getId();
        this.name = eMedia.getName();
        this.title = eMedia.getTitle();
        this.description = eMedia.getDescription();
        this.type = eMedia.getType();
    }

    @SerializedName("id")
    private
    Integer id;

    @SerializedName("name")
    private
    String name;

    @SerializedName("title")
    private
    String title;

    @SerializedName("description")
    private
    String description;

    @SerializedName("type")
    private
    String type;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }
}
