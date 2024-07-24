package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class SurveyCountItem {
    @SerializedName("error")
    private boolean error;

    @SerializedName("survey_count")
    private int surveyCount;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getSurveyCount() {
        return surveyCount;
    }

    public void setSurveyCount(int surveyCount) {
        this.surveyCount = surveyCount;
    }
}
