package com.vinodmapari.aaplasevak.Model;

public class WaterSupply
{
    String id, slot_name;

    public WaterSupply(String id, String slot_name) {
        this.id = id;
        this.slot_name = slot_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSlot_name() {
        return slot_name;
    }

    public void setSlot_name(String slot_name) {
        this.slot_name = slot_name;
    }
}
