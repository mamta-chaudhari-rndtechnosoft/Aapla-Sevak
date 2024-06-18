package com.vinodmapari.aaplasevak.ApiConfig;

import com.vinodmapari.aaplasevak.Model.HomeOptionsResponseData;
import com.vinodmapari.aaplasevak.Model.HomeSplashItem;
import com.vinodmapari.aaplasevak.Model.HomeSplashResponse;
import com.vinodmapari.aaplasevak.Model.TemplateResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("splash")
    Call<HomeSplashResponse> getSplash();

    @GET("option")
    Call<HomeOptionsResponseData> getHomeData();

    @GET("template_desc")
    Call<TemplateResponse> getTemplateData(@Query("template_id") String templateId);

}
