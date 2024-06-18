package com.vinodmapari.aaplasevak.Model;

public class Colony
{
    String id, colony_name;

    public Colony(String id, String colony_name) {
        this.id = id;
        this.colony_name = colony_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColony_name() {
        return colony_name;
    }

    public void setColony_name(String colony_name) {
        this.colony_name = colony_name;
    }
}
