package com.drabarz.karola.raillearn.model;

import java.io.Serializable;

public class Trip  implements Serializable{

    private String id;
    private final User user;
    private final Offer offer;
    private final Route route;

    public Trip(String id, User user, Offer offer, Route route) {
        this.id = id;
        this.user = user;
        this.offer = offer;
        this.route = route;
    }

    public Trip(User user, Offer offer, Route route) {
        this.id = "0";
        this.user = user;
        this.offer = offer;
        this.route = route;
    }

    public User getUser() {
        return user;
    }

    public Offer getOffer() {
        return offer;
    }

    public Route getRoute() {
        return route;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
