package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class WaterSupplyItem {

    @SerializedName("error")
    private String error;

    @SerializedName("id")
    private String id;

    @SerializedName("slot_name")
    private String slotName;

    //Getters and Setters

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

    public String getSlotName() {
        return slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }
}
