package com.github.gmetal.jsonplaceholderapi;

import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("postId")
    private int postId;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("body")
    private String body;

    public Comment(final int postId, final int id, final String name, final String email, final String body) {

        this.postId = postId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public int getPostId() {

        return postId;
    }

    public void setPostId(final int postId) {

        this.postId = postId;
    }

    public int getId() {

        return id;
    }

    public void setId(final int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(final String name) {

        this.name = name;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(final String email) {

        this.email = email;
    }

    public String getBody() {

        return body;
    }

    public void setBody(final String body) {

        this.body = body;
    }
}
