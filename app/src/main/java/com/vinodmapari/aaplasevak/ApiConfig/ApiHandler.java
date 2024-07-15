package com.vinodmapari.aaplasevak.ApiConfig;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiHandler {

    private static final String BASE_URL = "https://aaplasevak.com/";

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {

        if(retrofit == null){

            OkHttpClient client = new OkHttpClient.Builder().build();

            Gson gson = new GsonBuilder().setLenient().create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit;
    }
    //.addConverterFactory(ScalarsConverterFactory.create())
}