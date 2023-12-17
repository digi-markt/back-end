package com.hochschule.digimarkt.model;

public class AddRequest {
    private String title;
    private String description;
    private int category;
    private double price;

    private boolean isFree;
    private String mediaURL;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isFree() {
        return isFree;
    }

    public String getMediaURL() {
        return mediaURL;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public void setMediaURL(String mediaURL) {
        this.mediaURL = mediaURL;
    }
}
