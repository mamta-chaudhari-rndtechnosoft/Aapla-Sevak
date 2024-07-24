package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class ColonyItem {

    @SerializedName("error")
    private String error;

    @SerializedName("id")
    private String id;

    @SerializedName("colony_name")
    private String colonyName;

    @SerializedName("series_id")
    private String seriesId;


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

    public String getColonyName() {
        return colonyName;
    }

    public void setColonyName(String colonyName) {
        this.colonyName = colonyName;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }
}
