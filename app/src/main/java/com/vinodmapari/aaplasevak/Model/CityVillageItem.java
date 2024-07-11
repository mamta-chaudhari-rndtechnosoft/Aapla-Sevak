package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class CityVillageItem {
    private String error;
    private String id;
    @SerializedName("city_village_name")
    private String cityVillageName;

    // Getters and Setters
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityVillageName() {
        return cityVillageName;
    }

    public void setCityVillageName(String cityVillageName) {
        this.cityVillageName = cityVillageName;
    }

    // Override toString() for easy debugging
    @Override
    public String toString() {
        return "CityVillageItem{" +
                "error='" + error + '\'' +
                ", id='" + id + '\'' +
                ", cityVillageName='" + cityVillageName + '\'' +
                '}';
    }
}

