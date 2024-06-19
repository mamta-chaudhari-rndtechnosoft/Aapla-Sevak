package com.vinodmapari.aaplasevak.ApiConfig;

import com.vinodmapari.aaplasevak.Model.DeleteMemberResponseData;
import com.vinodmapari.aaplasevak.Model.EditFamilyMemberBody;
import com.vinodmapari.aaplasevak.Model.HomeOptionsResponseData;
import com.vinodmapari.aaplasevak.Model.HomeSplashItem;
import com.vinodmapari.aaplasevak.Model.HomeSplashResponse;
import com.vinodmapari.aaplasevak.Model.HouseDetail;
import com.vinodmapari.aaplasevak.Model.HouseResponse;
import com.vinodmapari.aaplasevak.Model.TemplateResponse;
import com.vinodmapari.aaplasevak.Model.UserDetailResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("splash")
    Call<HomeSplashResponse> getSplash();

    @GET("option")
    Call<HomeOptionsResponseData> getHomeData();

    @GET("template_desc")
    Call<TemplateResponse> getTemplateData(@Query("template_id") String templateId);

    //Family Member list
    @GET("api.php?survey_detail_by_house_no")
    Call<HouseResponse> getHouseDetails(@Query("house_no") String house_no);

    @GET("api.php?update_survey_detail")
    Call<ResponseBody> updateFamilyDetails(@Body EditFamilyMemberBody editFamilyMemberBody);

    @GET("api.php?delete_survey_detail")
    Call<DeleteMemberResponseData> deleteFamilyMember(@Query("id") int id);

    @GET("api.php?user_detail_by_id")
    Call<UserDetailResponse> getUserDetail(@Query("id") int id);


}
