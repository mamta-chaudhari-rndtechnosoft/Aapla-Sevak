package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WaterSupplyResponse {

    @SerializedName("WATERSUPPLY")
    private List<WaterSupplyItem> waterSupply;

    public List<WaterSupplyItem> getWaterSupply() {
        return waterSupply;
    }

    public void setWaterSupply(List<WaterSupplyItem> waterSupply) {
        this.waterSupply = waterSupply;
    }
}
