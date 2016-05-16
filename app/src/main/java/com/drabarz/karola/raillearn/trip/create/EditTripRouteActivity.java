package com.drabarz.karola.raillearn.trip.create;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Route;
import com.drabarz.karola.raillearn.model.Trip;

public class EditTripRouteActivity extends TripRouteActivity {

    private final static String TRIP = "trip";
    private Trip trip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        trip = (Trip) getIntent().getSerializableExtra(TRIP);

        setTripData();
    }

    private void setTripData() {
        EditText inputDepartureStationNameEditText = ((EditText) findViewById(R.id.inputDepartureStationNameEditText));
        inputDepartureStationNameEditText.setText(trip.getRoute().getDeparture().getName());

        EditText inputDepartureStationDateEditText = ((EditText) findViewById(R.id.inputDepartureStationDateEditText));
        inputDepartureStationDateEditText.setText(trip.getRoute().getDeparture().getDate());

        EditText inputDepartureStationTimeEditText = ((EditText) findViewById(R.id.inputDepartureStationTimeEditText));
        inputDepartureStationTimeEditText.setText(trip.getRoute().getDeparture().getTime());

        EditText inputArrivalStationNameEditText = ((EditText) findViewById(R.id.inputArrivalStationNameEditText));
        inputArrivalStationNameEditText.setText(trip.getRoute().getArrival());
    }

    @Override
    protected void onConfirmRouteButtonClicked() {
        Route route = getRouteInputData();
        startNewTripOfferActivity(route);
    }

    private void startNewTripOfferActivity(Route route) {
        NewTripOfferActivity.start(this, route);
    }

    public static void start(Context context, Trip trip) {
        Intent intent = new Intent(context, EditTripRouteActivity.class);
        intent.putExtra(TRIP, trip);
        context.startActivity(intent);
    }
}
