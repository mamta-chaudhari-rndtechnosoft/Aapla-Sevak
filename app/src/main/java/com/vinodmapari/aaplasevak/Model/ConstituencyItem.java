package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class ConstituencyItem {
    private String error;
    private String id;

    @SerializedName("constituency_name")
    private String constituencyName;

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

    public String getConstituencyName() {
        return constituencyName;
    }

    public void setConstituencyName(String constituencyName) {
        this.constituencyName = constituencyName;
    }

    // Override toString() for easy debugging
    @Override
    public String toString() {
        return "ConstituencyItem{" +
                "error='" + error + '\'' +
                ", id='" + id + '\'' +
                ", constituencyName='" + constituencyName + '\'' +
                '}';
    }
}
