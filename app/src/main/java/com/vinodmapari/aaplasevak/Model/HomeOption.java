package com.vinodmapari.aaplasevak.Model;

public class HomeOption {
    private String id;
    private String name1;
    private String image;

    public HomeOption(String id, String name1, String image) {
        this.id = id;
        this.name1 = name1;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getName1() {
        return name1;
    }

    public String getImage() {
        return image;
    }
}

