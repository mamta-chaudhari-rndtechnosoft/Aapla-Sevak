package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PrintHouseDetailResponse {

    @SerializedName("PRINT_HOUSE_DETAIL")
    public ArrayList<FamilyDetailsItem> familyDetailsItems;

    public ArrayList<FamilyDetailsItem> getFamilyDetailsItems() {
        return familyDetailsItems;
    }

    public void setFamilyDetailsItems(ArrayList<FamilyDetailsItem> familyDetailsItems) {
        this.familyDetailsItems = familyDetailsItems;
    }
}
