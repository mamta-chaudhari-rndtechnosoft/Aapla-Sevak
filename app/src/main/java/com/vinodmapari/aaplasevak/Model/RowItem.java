package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class RowItem {
    @SerializedName("error")
    private String error;

    @SerializedName("id")
    private String id;

    @SerializedName("row_name")
    private String rowName;

    @SerializedName("series_id")
    private String seriesId;

    @SerializedName("colony_id")
    private String colonyId;


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

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getColonyId() {
        return colonyId;
    }

    public void setColonyId(String colonyId) {
        this.colonyId = colonyId;
    }
}
