package com.samuelbernard.starbhakattendance.rest;

import com.samuelbernard.starbhakattendance.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = BuildConfig.BASE_URL;
    public static final String IMAGE_URL = BuildConfig.IMAGE_URL;

    private static Retrofit retrofit = null;

    // Method for creating retrofit instance
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(StringConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}