package com.vinodmapari.aaplasevak.Model;

import java.util.ArrayList;

public class SliderModel  {

        private String id;
        private String banner_image;

     public SliderModel(String id, String banner_image)
    {
        this.id = id;
        this.banner_image = banner_image;
    }

    ArrayList<SliderModel>sliderModels=new ArrayList<>();

    public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBanner_image() {
            return banner_image;
        }

        public void setBanner_image(String banner_image) {
            this.banner_image = banner_image;
        }
}
