package com.drabarz.karola.raillearn.test;

import com.drabarz.karola.raillearn.model.Departure;
import com.drabarz.karola.raillearn.model.Route;
import com.drabarz.karola.raillearn.model.Trip;
import com.drabarz.karola.raillearn.TripItem;
import com.drabarz.karola.raillearn.adapter.TripsGroupAdapter;

public class TestData {

    public static void fillTripsListWithTestData(TripsGroupAdapter tripsGroupAdapter, int count) {
        Departure departure = new Departure("Warszawa","07.04.2016", "17:50");
        Route route = new Route(departure, "Kraków");
        Trip trip = new Trip("Karola", "Hiszpanski", route);

        for (int i = 0; i < count; i++){
            tripsGroupAdapter.addTripItem(new TripItem(trip));
        }
    }
}
