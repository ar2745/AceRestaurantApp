package com.example.acerestaurantapp;

public class Item {
    // Private fields for the item properties
    private String name;
    private String price;
    private double rating;
    private String description;
    private int imageResource;

    // Constructor to initialize the item with given values
    public Item(String name, String price, double rating, String description, int imageResource) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.imageResource = imageResource;
    }

    // Getter methods to access the item properties

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResource() {
        return imageResource;
    }
}
