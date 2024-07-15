package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchListResponseData {
    @SerializedName("SEARCH")
    private List<SearchListItem> searchList;

    public List<SearchListItem> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<SearchListItem> searchList) {
        this.searchList = searchList;
    }
}
