package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SurveyorResponseData {

    @SerializedName("SURVEYOR")
    ArrayList<SurveyorItem> Surveyor;

    public ArrayList<SurveyorItem> getSurveyor() {
        return Surveyor;
    }

    public void setSurveyor(ArrayList<SurveyorItem> surveyor) {
        Surveyor = surveyor;
    }
}
