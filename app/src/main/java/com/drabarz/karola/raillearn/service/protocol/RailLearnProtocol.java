package com.drabarz.karola.raillearn.service.protocol;

import com.google.gson.Gson;

public abstract class RailLearnProtocol {

    protected String action;

    public String getJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
