package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class SearchListBody {

    String fullname;
    @SerializedName("voter_id")
    String voterId;

    @SerializedName("adhar_card")
    String adharCard;


    public SearchListBody(String fullname, String voterId, String adharCard) {
        this.fullname = fullname;
        this.voterId = voterId;
        this.adharCard = adharCard;
    }


}
