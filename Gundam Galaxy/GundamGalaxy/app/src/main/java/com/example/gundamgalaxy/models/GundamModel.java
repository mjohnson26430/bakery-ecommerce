package com.example.gundamgalaxy.models;

public class GundamModel {
    private String name;
    private String releaseYear;
    private String synopsis;
    private double price;
    private int image;

    public GundamModel(String name, String releaseYear, String synopsis, double price, int image) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.synopsis = synopsis;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public double getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }
}
