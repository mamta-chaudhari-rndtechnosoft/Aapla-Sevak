package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ColonyListResponseData {
    @SerializedName("COLONY_LIST")
    private ArrayList<ColonyItem> colonies;

    public ArrayList<ColonyItem> getColonies() {
        return colonies;
    }

    public void setColonies(ArrayList<ColonyItem> colonies) {
        this.colonies = colonies;
    }
}
