package com.vinodmapari.aaplasevak.Model;

import java.util.ArrayList;

public class Wp_Img {
    String id, image;

    public Wp_Img(String id, String image) {

        this.id = id;
        this.image = image;
    }

    ArrayList<Wp_Img> wp_imgs=new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
