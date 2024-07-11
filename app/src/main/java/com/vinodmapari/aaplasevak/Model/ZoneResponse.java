package com.vinodmapari.aaplasevak.Model;

import java.util.List;

public class ZoneResponse {
    private List<ZoneItem> zone;

    // Getters and Setters
    public List<ZoneItem> getZone() {
        return zone;
    }

    public void setZone(List<ZoneItem> zone) {
        this.zone = zone;
    }

    // Override toString() for easy debugging
    @Override
    public String toString() {
        return "ZoneResponse{" +
                "zone=" + zone +
                '}';
    }
}
