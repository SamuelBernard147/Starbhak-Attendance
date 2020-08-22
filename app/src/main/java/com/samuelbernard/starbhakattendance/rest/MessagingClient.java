package com.samuelbernard.starbhakattendance.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessagingClient {
    private static final String BASE_URL = "https://fcm.googleapis.com/";

    private static Retrofit retrofit = null;

    // Method for creating retrofit instance
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}