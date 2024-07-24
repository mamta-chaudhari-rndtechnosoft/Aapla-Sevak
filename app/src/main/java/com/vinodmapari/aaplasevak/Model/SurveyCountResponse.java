package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SurveyCountResponse {

    @SerializedName("survey")
    List<SurveyCountItem> surveyCountItems;

    public List<SurveyCountItem> getSurveyCountItems() {
        return surveyCountItems;
    }

    public void setSurveyCountItems(List<SurveyCountItem> surveyCountItems) {
        this.surveyCountItems = surveyCountItems;
    }
}
