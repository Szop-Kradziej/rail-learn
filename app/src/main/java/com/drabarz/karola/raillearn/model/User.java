package com.drabarz.karola.raillearn.model;

import java.io.Serializable;

public class User implements Serializable{

    private final String name;
    private final String id;
    private final String coverPhoto;
    private final String profilePhoto;

    public User(String name, String id, String coverPhoto, String profilePhoto) {
        this.name = name;
        this.id = id;
        this.coverPhoto = coverPhoto;
        this.profilePhoto = profilePhoto;
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

    public String getProfilePhoto() {
        return profilePhoto;
    }
}
