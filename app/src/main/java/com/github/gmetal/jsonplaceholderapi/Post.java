package com.github.gmetal.jsonplaceholderapi;

import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("id")
    private int id;
    @SerializedName("userId")
    private int userId;
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;

    public Post(final int id, final int userId, final String title, final String body) {

        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public int getId() {

        return id;
    }

    public void setId(final int id) {

        this.id = id;
    }

    public int getUserId() {

        return userId;
    }

    public void setUserId(final int userId) {

        this.userId = userId;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(final String title) {

        this.title = title;
    }

    public String getBody() {

        return body;
    }

    public void setBody(final String body) {

        this.body = body;
    }
}
