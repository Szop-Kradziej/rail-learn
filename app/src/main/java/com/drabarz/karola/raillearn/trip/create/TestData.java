package com.drabarz.karola.raillearn.trip.create;

import com.drabarz.karola.raillearn.model.Departure;
import com.drabarz.karola.raillearn.model.Offer;
import com.drabarz.karola.raillearn.model.Route;
import com.drabarz.karola.raillearn.model.User;

public class TestData {

    public static Route getExampleRoute() {
        Departure departure = new Departure("Warszawa","07.04.2016", "17:50");
        return new Route(departure, "Kraków");
    }

    public static Offer getExampleOffer() {
        return new Offer("Hiszpanski","To jest opis");
    }

    public static User getExampleUser() {
        return new User("Karola");
    }
}
