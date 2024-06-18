package com.vinodmapari.aaplasevak.Model;

public class Row
{
    String id, row_name;

    public Row(String id, String row_name) {
        this.id = id;
        this.row_name = row_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRow_name() {
        return row_name;
    }

    public void setRow_name(String row_name) {
        this.row_name = row_name;
    }
}
