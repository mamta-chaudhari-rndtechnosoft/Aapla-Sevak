package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class ZoneItem {
    private String error;
    private String id;
    @SerializedName("zone_name")
    private String zoneName;

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

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    // Override toString() for easy debugging
    @Override
    public String toString() {
        return "ZoneItem{" +
                "error='" + error + '\'' +
                ", id='" + id + '\'' +
                ", zoneName='" + zoneName + '\'' +
                '}';
    }
}

