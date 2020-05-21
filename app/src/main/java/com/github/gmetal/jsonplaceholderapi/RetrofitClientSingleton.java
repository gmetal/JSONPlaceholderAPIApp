package com.github.gmetal.jsonplaceholderapi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientSingleton {

    private Retrofit retrofit;
    private static RetrofitClientSingleton sInstance;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private RetrofitClientSingleton() {

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
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