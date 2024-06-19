package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserDetailResponse {
    @SerializedName("USER_DETAIL")
    private ArrayList<UserDetail> userDetails;

    public ArrayList<UserDetail> getUserDetails() {
        return userDetails;
    }

    public void setHouseDetails(ArrayList<UserDetail> userDetails) {
        this.userDetails = userDetails;
    }
}
