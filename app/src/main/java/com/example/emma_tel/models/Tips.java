package com.example.emma_tel.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Tips implements Serializable

    {

        @SerializedName("id")
        private
        Integer id;

        @SerializedName("content")
        private
        String content;

        public Tips(String content) {
            this.content = content;
        }

        public Integer getId() {
            return id;
        }

        public String getContent() {
            return content;
        }
    }
