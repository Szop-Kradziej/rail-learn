package com.drabarz.karola.raillearn.service.protocol;

import com.drabarz.karola.raillearn.model.Trip;

public class NewTripRequest extends RailLearnProtocol{

    private final Trip trip;

    public NewTripRequest(Trip trip) {
        this.action = "create";
        this.trip = trip;
    }
}
