package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CasteResponse {
    @SerializedName("caste")
    private List<CasteItem> castes;

    public List<CasteItem> getCastes() {
        return castes;
    }

    public void setCastes(List<CasteItem> castes) {
        this.castes = castes;
    }
}
