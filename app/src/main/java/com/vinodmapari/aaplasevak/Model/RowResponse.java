package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RowResponse {

    @SerializedName("ROW")
    private List<RowItem> rows;

    public List<RowItem> getRows() {
        return rows;
    }

    public void setRows(List<RowItem> rows) {
        this.rows = rows;
    }
}
