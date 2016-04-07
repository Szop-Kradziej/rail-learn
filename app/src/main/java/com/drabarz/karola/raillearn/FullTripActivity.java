package com.drabarz.karola.raillearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.drabarz.karola.raillearn.model.Trip;

public class FullTripActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_trip_activity);

        Trip trip = (Trip) getIntent().getSerializableExtra(TripsActivity.TRIP);
        Log.i("FullTripsActivity", "Get trip: " + trip.getTripTitle());
    }
}
