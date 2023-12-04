package com.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameInfo {
    private String name;
    private Double rating;
    @JsonProperty("background_image")
    private String backgroundImage;
    private String description;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }
    public String getBackgroundImage() {
        return backgroundImage;
    }
    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
