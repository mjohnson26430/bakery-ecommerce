package com.example.gundamgalaxy;

public class Category {
    private String name;
    private int imageResource;
    private  String id;

    public Category(String name, int imageResource, String id) {
        this.name = name;
        this.imageResource = imageResource;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getImageResource() {
        return imageResource;
    }
}
