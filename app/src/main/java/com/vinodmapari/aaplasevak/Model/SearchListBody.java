package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class SearchListBody {

    String fullname;

    @SerializedName("voter_id")
    String voterId;

    @SerializedName("adhar_card")
    String adharCard;

    @SerializedName("house_no")
    String houseNo;

    @SerializedName("mobile")
    String mobileNum;

    @SerializedName("mobile2")
    String mobileNumTwo;

    public SearchListBody(String fullname, String voterId, String adharCard, String houseNo, String mobileNum) {
        this.fullname = fullname;
        this.voterId = voterId;
        this.adharCard = adharCard;
        this.houseNo = houseNo;
        this.mobileNum = mobileNum;

    }
}
