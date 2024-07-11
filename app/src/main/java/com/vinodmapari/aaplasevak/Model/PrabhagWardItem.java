package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class PrabhagWardItem {
    private String error;
    private String id;
    @SerializedName("prabhag_ward_name")
    private String prabhagWardName;

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

    public String getPrabhagWardName() {
        return prabhagWardName;
    }

    public void setPrabhagWardName(String prabhagWardName) {
        this.prabhagWardName = prabhagWardName;
    }

    // Override toString() for easy debugging
    @Override
    public String toString() {
        return "PrabhagWardItem{" +
                "error='" + error + '\'' +
                ", id='" + id + '\'' +
                ", prabhagWardName='" + prabhagWardName + '\'' +
                '}';
    }
}

