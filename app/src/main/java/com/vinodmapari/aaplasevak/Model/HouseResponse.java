package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HouseResponse {
    @SerializedName("HOUSE_DETAIL")
    private ArrayList<HouseDetail> houseDetails;

    public ArrayList<HouseDetail> getHouseDetails() {
        return houseDetails;
    }

    public void setHouseDetails(ArrayList<HouseDetail> houseDetails) {
        this.houseDetails = houseDetails;
    }
}
