package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class RegisterBody {

    @SerializedName("name")
    String name;
    @SerializedName("middle_name")
    String middleName;

    @SerializedName("surname")
    String surName;

    @SerializedName("mobile")
    String number;
    @SerializedName("password")
    String password;

    public RegisterBody() {
    }

    public RegisterBody(String name, String middleName, String surName, String number, String password) {
        this.name = name;
        this.middleName = middleName;
        this.surName = surName;
        this.number = number;
        this.password = password;
    }
}
