package com.drabarz.karola.raillearn.model;

import java.io.Serializable;

public class Departure implements Serializable {
    private final String name;
    private final String date;
    private final String time;

    public Departure(String name, String date, String time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
