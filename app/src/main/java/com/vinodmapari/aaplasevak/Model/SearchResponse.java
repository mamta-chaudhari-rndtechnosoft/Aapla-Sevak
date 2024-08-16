package com.vinodmapari.aaplasevak.Model;

import com.vinodmapari.aaplasevak.VoterSearchItem;

import java.util.ArrayList;
import java.util.List;

public class SearchResponse {
    private ArrayList<SearchItem> SEARCH;
    //private ArrayList<VoterSearchItem> SEARCH;

    // Getter and Setter
    public ArrayList<SearchItem> getSEARCH() {
        return SEARCH;
    }

    public void setSEARCH(ArrayList<SearchItem> SEARCH) {
        this.SEARCH = SEARCH;
    }
}