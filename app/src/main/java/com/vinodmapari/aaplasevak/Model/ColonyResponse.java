package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ColonyResponse {

    @SerializedName("COLONY")
    private List<ColonyItem> colonies;

    public List<ColonyItem> getColonies() {
        return colonies;
    }

    public void setColonies(List<ColonyItem> colonies) {
        this.colonies = colonies;
    }
}
