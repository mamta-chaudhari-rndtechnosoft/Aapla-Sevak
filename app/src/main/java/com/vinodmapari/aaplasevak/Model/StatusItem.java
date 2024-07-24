package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class StatusItem {

    @SerializedName("error")
    private String error;

    @SerializedName("id")
    private String id;

    @SerializedName("status_name")
    private String statusName;

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

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
