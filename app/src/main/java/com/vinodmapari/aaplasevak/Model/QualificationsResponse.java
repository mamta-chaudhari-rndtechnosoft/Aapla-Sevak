package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QualificationsResponse {
    @SerializedName("qualification")
    private List<QualificationItem> qualifications;

    public List<QualificationItem> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<QualificationItem> qualifications) {
        this.qualifications = qualifications;
    }


}
