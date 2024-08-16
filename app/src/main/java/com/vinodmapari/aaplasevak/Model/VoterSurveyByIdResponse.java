package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VoterSurveyByIdResponse {

    @SerializedName("SURVEY_DETAIL")
    List<SurveyDetailItem> surveyDetailItems;

    public List<SurveyDetailItem> getSurveyDetailItems() {
        return surveyDetailItems;
    }

    public void setVoterCountItems(List<SurveyDetailItem> surveyDetailItems) {
        this.surveyDetailItems = surveyDetailItems;
    }

}
