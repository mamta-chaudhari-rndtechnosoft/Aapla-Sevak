package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SeriesResponse {

    @SerializedName("SERIES")
    private List<SeriesItem> series;

    // Getters and Setters
    public List<SeriesItem> getSeries() {
        return series;
    }

    public void setSeries(List<SeriesItem> series) {
        this.series = series;
    }
}
