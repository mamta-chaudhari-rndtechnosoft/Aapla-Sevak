package com.vinodmapari.aaplasevak.Model;

public class SMS {
    String id,series_id,colony_id,template_name;


    public SMS(String id, String series_id, String colony_id, String template_name)
    {
        this.id = id;
        this.series_id = series_id;
        this.colony_id = colony_id;
        this.template_name = template_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeries_id() {
        return series_id;
    }

    public void setSeries_id(String series_id) {
        this.series_id = series_id;
    }

    public String getColony_id() {
        return colony_id;
    }

    public void setColony_id(String colony_id) {
        this.colony_id = colony_id;
    }

    public String getTemplate_name() {
        return template_name;
    }

    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }

}
