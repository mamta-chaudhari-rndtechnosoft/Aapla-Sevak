package com.vinodmapari.aaplasevak.Model;

import com.vinodmapari.aaplasevak.BuildConfig;

import java.util.ArrayList;

public class Constants {

    public static String MY_PREFS_NAME = BuildConfig.APPLICATION_ID;
    public static boolean flag = true;
    public static String Base_Url = "https://aaplasevak.com/";
    public static String url = Base_Url + "api.php?";
    //public static String addSurveyUrl = Base_Url + "add_survey_api.php";

    public static ArrayList<Series> series = new ArrayList<>();
    public static ArrayList<String> series_name = new ArrayList<>();

    public static ArrayList<Status> statuses = new ArrayList<>();
    public static ArrayList<String> status_name = new ArrayList<>();

    public static ArrayList<Colony> colonies = new ArrayList<>();
    public static ArrayList<String> colony_name = new ArrayList<>();

    public static ArrayList<Row> rows = new ArrayList<>();
    public static ArrayList<String> row_name = new ArrayList<>();

    public static ArrayList<WaterSupply> waterSupplies = new ArrayList<>();
    public static ArrayList<String> water_supply_slots = new ArrayList<>();

    public static ArrayList<String> constituency_name = new ArrayList<>();
    public static ArrayList<String> zone_name = new ArrayList<>();
    public static ArrayList<String> city_village_name = new ArrayList<>();
    public static ArrayList<String> prabhag_ward_name = new ArrayList<>();


    public static ArrayList<Relations> relations = new ArrayList<>();
    public static ArrayList<String> relation_name = new ArrayList<>();
    public static ArrayList<Template> templates = new ArrayList<>();
    public static ArrayList<String> template_name = new ArrayList<>();
    public static ArrayList<SliderModel> sliderModels = new ArrayList<>();

    // Login Api GET
    public static String loginURL = url + "login";

    // Splash Api GET
    public static String splashURL = url + "splash";

    public static String templateDec = url + "template_desc";

    public static String homePageOptions = url + "option";


    public static String houseDetail = url + "survey_detail_by_house_no";

    //series Api GET
    public static String series_list = url + "series";

    //status Api GET
    public static String status_list = url + "status";

    //colony Api GET
    public static String colony_list = url + "colony";

    //row Api GET
    public static String row_list = url + "row";

    //status Api GET
    public static String relation_list = url + "relation";

    //banner Api
    public static String banner = url + "banner";

    //search Api
    //public static String search_list = url + "search_survey_detail";
    public static String search_list = Base_Url + "search_survey_detail.php";

    //WaterSupply Api
    public static String water_Supply_list = url + "watersupply";

    //add_Survey Api
    public static String add_survey = Base_Url + "add_survey_api.php";

    //add_member Api
    public static String add_member = url + "add_member";

    //add_main_member_detail get Api
    public static String main_member_detail = url + "main_member_detail";

    public static String template = url + "template";

    public static String whatsapp = url + "wts_no";

    public static String whatsapp_img = url + "wts_img";

    public static String send_sms = url + "sms_send";

}
