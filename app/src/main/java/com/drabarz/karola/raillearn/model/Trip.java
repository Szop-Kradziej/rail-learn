package com.drabarz.karola.raillearn.model;

import java.io.Serializable;

public class Trip  implements Serializable{

    private final User user;
    private final Offer offer;
    private final Route route;

    public Trip(User user, Offer offer, Route route) {
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
}
