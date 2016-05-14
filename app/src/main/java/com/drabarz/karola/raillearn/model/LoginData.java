package com.drabarz.karola.raillearn.model;

public class LoginData {
    private final String token;
    private final String id;

    public LoginData(String token, String id) {
        this.token = token;
        this.id = id;
    }

    public String getToken() {
        return token;
    }
    public String getId() {
        return id;
    }
}
