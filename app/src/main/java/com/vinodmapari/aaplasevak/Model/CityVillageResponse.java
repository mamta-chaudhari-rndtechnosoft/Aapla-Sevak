package com.vinodmapari.aaplasevak.Model;

import java.util.List;

public class CityVillageResponse {
    private List<CityVillageItem> city_village;

    // Getters and Setters
    public List<CityVillageItem> getCityVillage() {
        return city_village;
    }

    public void setCityVillage(List<CityVillageItem> city_village) {
        this.city_village = city_village;
    }

    // Override toString() for easy debugging
    @Override
    public String toString() {
        return "CityVillageResponse{" +
                "city_village=" + city_village +
                '}';
    }
}
