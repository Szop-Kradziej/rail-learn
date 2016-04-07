package com.drabarz.karola.raillearn.test;

import com.drabarz.karola.raillearn.Trip;
import com.drabarz.karola.raillearn.TripItem;
import com.drabarz.karola.raillearn.adapter.TripsGroupAdapter;

public class TestData {

    public static void fillTripsListWithTestData(TripsGroupAdapter tripsGroupAdapter, int count) {
        Trip trip = new Trip("Karola", "Hiszpanski", "Warszawa-Krakow", "07.04.2016 17:50");

        for (int i = 0; i < count; i++){
            tripsGroupAdapter.addTripItem(new TripItem(trip));
        }
    }
}
