package com.vinodmapari.aaplasevak.Model;

import java.util.List;

public class PrabhagWardResponse {
    private List<PrabhagWardItem> prabhag_ward;

    // Getters and Setters
    public List<PrabhagWardItem> getPrabhagWard() {
        return prabhag_ward;
    }

    public void setPrabhagWard(List<PrabhagWardItem> prabhag_ward) {
        this.prabhag_ward = prabhag_ward;
    }

    // Override toString() for easy debugging
    @Override
    public String toString() {
        return "PrabhagWardResponse{" +
                "prabhag_ward=" + prabhag_ward +
                '}';
    }
}
