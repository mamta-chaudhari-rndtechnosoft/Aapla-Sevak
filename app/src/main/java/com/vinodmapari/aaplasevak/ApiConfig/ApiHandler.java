package com.vinodmapari.aaplasevak.ApiConfig;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHandler {

    private static final String BASE_URL = "https://aaplasevak.com/api.php?";



    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {

        if(retrofit == null){

            OkHttpClient client = new OkHttpClient.Builder().build();

            /*Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();*/


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}