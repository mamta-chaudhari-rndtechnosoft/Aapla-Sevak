package com.vinodmapari.aaplasevak.ApiConfig;

import com.vinodmapari.aaplasevak.Model.CityVillageResponse;
import com.vinodmapari.aaplasevak.Model.ConstituencyResponse;
import com.vinodmapari.aaplasevak.Model.DeleteMemberResponseData;
import com.vinodmapari.aaplasevak.Model.EditFamilyMemberBody;
import com.vinodmapari.aaplasevak.Model.EditMemberRespponseData;
import com.vinodmapari.aaplasevak.Model.HomeOptionsResponseData;
import com.vinodmapari.aaplasevak.Model.HomeSplashItem;
import com.vinodmapari.aaplasevak.Model.HomeSplashResponse;
import com.vinodmapari.aaplasevak.Model.HouseDetail;
import com.vinodmapari.aaplasevak.Model.HouseResponse;
import com.vinodmapari.aaplasevak.Model.PrabhagWardResponse;
import com.vinodmapari.aaplasevak.Model.SendSmsBody;
import com.vinodmapari.aaplasevak.Model.SendSmsResponseData;
import com.vinodmapari.aaplasevak.Model.TemplateResponse;
import com.vinodmapari.aaplasevak.Model.UserDetailResponse;
import com.vinodmapari.aaplasevak.Model.WhatsAppNumberResponseData;
import com.vinodmapari.aaplasevak.Model.ZoneResponse;

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
    Call<HouseResponse> getHouseDetails(@Query("house_no") String house_no, @Query("series_id") int series_id);

    /*@POST("api.php?update_survey_detail")
    Call<ResponseBody> updateFamilyDetails(@Body EditFamilyMemberBody editFamilyMemberBody);*/
    @POST("surveyedit_api.php")
    Call<EditMemberRespponseData> updateFamilyDetails(@Body EditFamilyMemberBody editFamilyMemberBody);

    @GET("api.php?delete_survey_detail")
    Call<DeleteMemberResponseData> deleteFamilyMember(@Query("id") int id);

    @GET("api.php?user_detail_by_id")
    Call<UserDetailResponse> getUserDetail(@Query("id") int id);

    /*
    @POST("api.php?sms_send_new")
    Call<ResponseBody> sendSms(@Body SendSmsBody sendSmsBody);
    */
    @POST("sendsms_api.php")
    Call<SendSmsResponseData> sendSms(@Body SendSmsBody sendSmsBody);

    @GET("api.php?wts_no")
    Call<WhatsAppNumberResponseData> getWhatsAppContact(
            @Query("watersupply_id") String waterSupplyId,
            @Query("row_id") String rowId,
            @Query("colony_id") String colonyId,
            @Query("series_id") String seriesId
    );

    @GET("api.php?constituency")
    Call<ConstituencyResponse> getConstituencyList();

    @GET("api.php?city_village")
    Call<CityVillageResponse> getCityVillage();

    @GET("api.php?zone")
    Call<ZoneResponse> getZoneList();

    @GET("api.php?prabhag_ward")
    Call<PrabhagWardResponse> getPrabhagWardList();
}
