package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class LoginResponseData {
    @SerializedName("USER")
    private LoggedInUser loggedInUser;

    public LoggedInUser getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(LoggedInUser loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}


