package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AddMultipleSurveyBody {

    private ArrayList<SurveyDetailItem> surveyData;

    public AddMultipleSurveyBody(ArrayList<SurveyDetailItem> surveyData) {
        this.surveyData = surveyData;
    }

    public ArrayList<SurveyDetailItem> getSurveyData() {
        return surveyData;
    }

    public void setSurveyData(ArrayList<SurveyDetailItem> surveyData) {
        this.surveyData = surveyData;
    }

}
