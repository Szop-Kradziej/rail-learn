package com.drabarz.karola.raillearn.model;

import java.io.Serializable;

public class User implements Serializable{

    private final String name;
    private final String id;
    private  String coverPhoto;

    public User(String name, String id, String coverPhoto) {
        this.name = name;
        this.id = id;
        this.coverPhoto = coverPhoto;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }
}
