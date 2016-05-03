package com.drabarz.karola.raillearn.trip.create;

import com.drabarz.karola.raillearn.model.Offer;
import com.drabarz.karola.raillearn.model.User;

public class TestData {

    public static String getArrival() {
        return "Kraków";
    }

    public static Offer getExampleOffer() {
        return new Offer("Hiszpanski","To jest opis");
    }

    public static User getExampleUser() {
        return new User("Karola");
    }
}
