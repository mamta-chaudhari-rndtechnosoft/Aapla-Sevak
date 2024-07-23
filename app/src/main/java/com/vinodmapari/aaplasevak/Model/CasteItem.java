package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class CasteItem {

    @SerializedName("error")
    private String error;

    @SerializedName("id")
    private String id;

    @SerializedName("caste_name")
    private String casteName;

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

    public String getCasteName() {
        return casteName;
    }

    public void setCasteName(String casteName) {
        this.casteName = casteName;
    }
}

