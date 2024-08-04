package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VoterCountResponse {

    @SerializedName("voter")
    List<VoterCountItem> voterCountItems;

    public List<VoterCountItem> getVoterCountItems() {
        return voterCountItems;
    }

    public void setVoterCountItems(List<VoterCountItem> voterCountItems) {
        this.voterCountItems = voterCountItems;
    }

}
