package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class QualificationItem {
    @SerializedName("error")
    private String error;

    @SerializedName("id")
    private String id;

    @SerializedName("qualification_name")
    private String qualificationName;

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

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }
}

