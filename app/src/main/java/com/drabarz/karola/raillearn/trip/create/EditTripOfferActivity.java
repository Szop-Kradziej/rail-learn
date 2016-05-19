package com.drabarz.karola.raillearn.trip.create;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Route;
import com.drabarz.karola.raillearn.model.Trip;
import com.drabarz.karola.raillearn.trip.details.UpdatedFullTripActivity;

public class EditTripOfferActivity extends TripOfferActivity {

    private final static String TRIP = "trip";
    private Trip trip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        trip = (Trip) getIntent().getSerializableExtra(TRIP);

        setTripData();
    }

    private void setTripData() {
        EditText inputTripTitleEditText = ((EditText) findViewById(R.id.inputTripTitleEditText));
        inputTripTitleEditText.setText(trip.getRoute().getDeparture().getName());

        EditText inputTripDescriptionEditText = ((EditText) findViewById(R.id.inputTripDescriptionEditText));
        inputTripDescriptionEditText.setText(trip.getRoute().getDeparture().getDate());
    }

    @Override
    protected void onConfirmOfferButtonClicked() {
        Trip editedTrip = createTripFromInputData();
        saveDescription(editedTrip.getOffer().getDescription());
        startNewFullTripActivity(editedTrip);
    }

    private void startNewFullTripActivity(Trip trip) {
        UpdatedFullTripActivity.start(this, trip);
    }

    public static void start(Context context, Trip trip, Route route) {
        Intent intent = new Intent(context, EditTripOfferActivity.class);
        intent.putExtra("trip", trip);
        intent.putExtra(ROUTE, route);
        context.startActivity(intent);
    }
}
