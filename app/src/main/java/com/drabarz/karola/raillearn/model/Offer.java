package com.drabarz.karola.raillearn.model;

import java.io.Serializable;

public class Offer implements Serializable{
    private final String title;
    private final String description;

    public Offer(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
