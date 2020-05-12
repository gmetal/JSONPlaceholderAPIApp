package com.github.gmetal.jsonplaceholderapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientSingleton {

    private Retrofit retrofit;
    private static RetrofitClientSingleton sInstance;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private RetrofitClientSingleton() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitClientSingleton getInstance() {

        if (sInstance == null) {
            sInstance = new RetrofitClientSingleton();
        }
        return sInstance;
    }

    public Retrofit getRetrofit() {

        return retrofit;
    }
}