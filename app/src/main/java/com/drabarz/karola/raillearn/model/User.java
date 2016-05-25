package com.drabarz.karola.raillearn.model;

import java.io.Serializable;

public class User implements Serializable{

    private final String name;
    private final String id;
    private final String coverPhoto;
    private final String profilPhoto;

    public User(String name, String id, String coverPhoto, String profilPhoto) {
        this.name = name;
        this.id = id;
        this.coverPhoto = coverPhoto;
        this.profilPhoto = profilPhoto;
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

    public String getProfilPhoto() {
        return profilPhoto;
    }
}
