package com.drabarz.karola.raillearn.model;

import java.io.Serializable;

public class Trip  implements Serializable{

    private final String userName;
    private final Offer offer;
    private final Route route;

    public Trip(String userName, Offer offer, Route route) {
        this.userName = userName;
        this.offer = offer;
        this.route = route;
    }

    public String getUserName() {
        return userName;
    }

    public Offer getOffer() {
        return offer;
    }

    public Route getRoute() {
        return route;
    }
}
