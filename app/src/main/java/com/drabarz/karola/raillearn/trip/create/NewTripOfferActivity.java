package com.drabarz.karola.raillearn.trip.create;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Route;
import com.drabarz.karola.raillearn.model.Trip;
import com.drabarz.karola.raillearn.trip.details.NewFullTripActivity;

public class NewTripOfferActivity extends TripOfferActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(isDescriptionSaved()) {
            setSavedDescription();
        }
    }

    private boolean isDescriptionSaved() {
        return PreferenceManager.getDefaultSharedPreferences(this).contains(DESCRIPTION);
    }

    private void setSavedDescription() {
        String description = PreferenceManager.getDefaultSharedPreferences(this).getString(DESCRIPTION,null);
        EditText inputTripDescriptionEditText = (EditText) findViewById(R.id.inputTripDescriptionEditText);
        inputTripDescriptionEditText.setText(description);
    }

    protected void onConfirmOfferButtonClicked() {
        Trip trip = createTripFromInputData();
        saveDescription(trip.getOffer().getDescription());
        startNewFullTripActivity(trip);
    }

    private void startNewFullTripActivity(Trip trip) {
        NewFullTripActivity.start(this, trip);
    }

    public static void start(Context context, Route route) {
        Intent intent = new Intent(context, NewTripOfferActivity.class);
        intent.putExtra(ROUTE, route);
        context.startActivity(intent);
    }
}
