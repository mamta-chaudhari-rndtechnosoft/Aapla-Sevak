package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatusResponse {
    @SerializedName("STATUS")
    private List<StatusItem> status;
    public List<StatusItem> getStatus() {
        return status;
    }
    public void setStatus(List<StatusItem> status) {
        this.status = status;
    }
}
