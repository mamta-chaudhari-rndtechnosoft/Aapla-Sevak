package com.vinodmapari.aaplasevak.Model;

import java.util.ArrayList;
import java.util.List;

public class ConstituencyResponse {
    private ArrayList<ConstituencyItem> Constituency;

    // Getters and Setters
    public ArrayList<ConstituencyItem> getConstituency() {
        return Constituency;
    }

    public void setConstituency(ArrayList<ConstituencyItem> Constituency) {
        this.Constituency = Constituency;
    }




    // Override toString() for easy debugging
    @Override
    public String toString() {
        return "ConstituencyResponse{" +
                "Constituency=" + Constituency +
                '}';
    }
}
