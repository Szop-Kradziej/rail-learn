package com.drabarz.karola.raillearn.model;

public class Trip {

    private final String userName;
    private final String tripTitle;
    private final Route route;

    public Trip(String userName, String tripTitle, Route route) {
        this.userName = userName;
        this.tripTitle = tripTitle;
        this.route = route;
    }

    public String getUserName() {
        return userName;
    }

    public String getTripTitle() {
        return tripTitle;
    }

    public Route getRoute() {
        return route;
    }
}
