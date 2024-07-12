package com.vinodmapari.aaplasevak.Model;

public class SearchBody {
    String fullname;

    public SearchBody(String fullname) {
        this.fullname = fullname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
