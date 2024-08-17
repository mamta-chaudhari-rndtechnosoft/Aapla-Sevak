package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class VoterSurveyByIdResponse {

    @SerializedName("SURVEY_DETAIL")
    ArrayList<SurveyDetailItem> surveyDetailItems;

    public ArrayList<SurveyDetailItem> getSurveyDetailItems() {
        return surveyDetailItems;
    }

    public void setVoterCountItems(ArrayList<SurveyDetailItem> surveyDetailItems) {
        this.surveyDetailItems = surveyDetailItems;
    }

}
