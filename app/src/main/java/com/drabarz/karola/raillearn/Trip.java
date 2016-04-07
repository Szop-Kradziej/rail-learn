package com.drabarz.karola.raillearn;

public class Trip {

    private final String userName;
    private final String tripTitle;
    private final String tripRoute;
    private final String tripDate;

    public Trip(String userName, String tripTitle, String tripDescription, String tripDate) {
        this.userName = userName;
        this.tripTitle = tripTitle;
        this.tripRoute = tripDescription;
        this.tripDate = tripDate;
    }

    public String getUserName() {
        return userName;
    }

    public String getTripTitle() {
        return tripTitle;
    }

    public String getTripRoute() {
        return tripRoute;
    }

    public String getTripDate() {
        return tripDate;
    }
}
