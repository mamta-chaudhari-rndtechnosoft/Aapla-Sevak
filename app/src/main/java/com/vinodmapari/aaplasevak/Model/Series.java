package com.vinodmapari.aaplasevak.Model;

public class Series
{
    String id, series_name;

    public Series(String id, String series_name) {
        this.id = id;
        this.series_name = series_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeries_name() {
        return series_name;
    }

    public void setSeries_name(String series_name) {
        this.series_name = series_name;
    }
}
