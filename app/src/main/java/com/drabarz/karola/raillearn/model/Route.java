package com.drabarz.karola.raillearn.model;

import java.io.Serializable;
import java.util.List;

public class Route implements Serializable {
    private final Departure departure;
    private final String arrival;

    public Route(Departure departure, String arrival) {
        this.departure = departure;
        this.arrival = arrival;
    }

    public Departure getDeparture() {
        return departure;
    }

    public String getFromToRoute() {
        String fromToRoute = departure.getName() + " - " + arrival;
        return fromToRoute;
    }
}
