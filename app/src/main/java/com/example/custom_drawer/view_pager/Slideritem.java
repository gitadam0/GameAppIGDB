package com.example.custom_drawer.view_pager;

public class Slideritem {
    private int image;
    private  String name;

    public Slideritem(int image, String name){

        this.image=image;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
