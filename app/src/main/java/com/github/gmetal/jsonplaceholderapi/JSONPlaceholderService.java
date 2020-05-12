package com.github.gmetal.jsonplaceholderapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSONPlaceholderService {

    @GET("/posts")
    Call<List<Post>> getAllPosts();

    @GET("/posts/{id}")
    Call<Post> getPostById(@Path("id") final int postId);
}
