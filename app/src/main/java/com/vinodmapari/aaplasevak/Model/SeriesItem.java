package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class SeriesItem {
    @SerializedName("error")
    private String error;

    @SerializedName("id")
    private String id;

    @SerializedName("series_name")
    private String seriesName;

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

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

}
